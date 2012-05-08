package Emgu.CV;

// ********* THIS FILE IS AUTO PORTED FORM C# USING CODEPORTING.COM *********

// ********* THIS FILE IS AUTO PORTED FORM C# USING CODEPORTING.COM *********

// ********* THIS FILE IS AUTO PORTED FORM C# USING CODEPORTING.COM *********

///////////////////////////////////////////////////////////////////////
// Change for test commit.
///////////////////////////////////////////////////////////////////////


   /// <summary>
   /// An object recognizer using PCA (Principle Components Analysis)
   /// </summary>
   
   public class EigenObjectRecognizer
   {
  private Image<Gray, Single>[] _eigenImages;
  private Image<Gray, Single> _avgImage;
  private Matrix<float>[] _eigenValues;
  private String[] _labels;
  private double _eigenDistanceThreshold;

  /// <summary>
  /// Get the eigen vectors that form the eigen space
  /// </summary>
  /// <remarks>The set method is primary used for deserialization, do not attemps to set it unless you know what you are doing</remarks>
  public Image<Gray, Single>[] getEigenImages() { return _eigenImages; }
 public void setEigenImages(Image<Gray, Single>[] value) { _eigenImages = value; }

  /// <summary>
  /// Get or set the labels for the corresponding training image
  /// </summary>
  public String[] getLabels() { return _labels; }
 public void setLabels(String[] value) { _labels = value; }

  /// <summary>
  /// Get or set the eigen distance threshold.
  /// The smaller the number, the more likely an examined image will be treated as unrecognized object. 
  /// Set it to a huge number (e.g. 5000) and the recognizer will always treated the examined image as one of the known object. 
  /// </summary>
  public double getEigenDistanceThreshold() { return _eigenDistanceThreshold; }
 public void setEigenDistanceThreshold(double value) { _eigenDistanceThreshold = value; }

  /// <summary>
  /// Get the average Image. 
  /// </summary>
  /// <remarks>The set method is primary used for deserialization, do not attemps to set it unless you know what you are doing</remarks>
  public Image<Gray, Single> getAverageImage() { return _avgImage; }
 public void setAverageImage(Image<Gray, Single> value) { _avgImage = value; }

  /// <summary>
  /// Get the eigen values of each of the training image
  /// </summary>
  /// <remarks>The set method is primary used for deserialization, do not attemps to set it unless you know what you are doing</remarks>
  public Matrix<float>[] getEigenValues() { return _eigenValues; }
 public void setEigenValues(Matrix<float>[] value) { _eigenValues = value; }

  private EigenObjectRecognizer()
  {
  }


  /// <summary>
  /// Create an object recognizer using the specific tranning data and parameters, it will always return the most similar object
  /// </summary>
  /// <param name="images">The images used for training, each of them should be the same size. It's recommended the images are histogram normalized</param>
  /// <param name="termCrit">The criteria for recognizer training</param>
  public EigenObjectRecognizer(Image<Gray, Byte>[] images, /*ref*/ MCvTermCriteria[] termCrit)
  {
  	this(images, GenerateLabels(images.Length), /*ref*/ termCrit);
	
  }

  private static String[] generateLabels(int size)
  {
     String[] labels = new String[size];
     for (int i = 0; i < size; i++)
        labels[i] = Integer.toString(i);
     return labels;
  }

  /// <summary>
  /// Create an object recognizer using the specific tranning data and parameters, it will always return the most similar object
  /// </summary>
  /// <param name="images">The images used for training, each of them should be the same size. It's recommended the images are histogram normalized</param>
  /// <param name="labels">The labels corresponding to the images</param>
  /// <param name="termCrit">The criteria for recognizer training</param>
  public EigenObjectRecognizer(Image<Gray, Byte>[] images, String[] labels, /*ref*/ MCvTermCriteria[] termCrit)
  {
  	this(images, labels, 0, /*ref*/ termCrit);
	
  }

  /// <summary>
  /// Create an object recognizer using the specific tranning data and parameters
  /// </summary>
  /// <param name="images">The images used for training, each of them should be the same size. It's recommended the images are histogram normalized</param>
  /// <param name="labels">The labels corresponding to the images</param>
  /// <param name="eigenDistanceThreshold">
  /// The eigen distance threshold, (0, ~1000].
  /// The smaller the number, the more likely an examined image will be treated as unrecognized object. 
  /// If the threshold is &lt; 0, the recognizer will always treated the examined image as one of the known object. 
  /// </param>
  /// <param name="termCrit">The criteria for recognizer training</param>
  public EigenObjectRecognizer(Image<Gray, Byte>[] images, String[] labels, double eigenDistanceThreshold, /*ref*/ MCvTermCriteria[] termCrit)
  {
     Debug.Assert(images.Length == labels.length, "The number of images should equals the number of labels");
     assert eigenDistanceThreshold >= 0.0 : "Eigen-distance threshold should always >= 0.0";

     <unknown>[] referenceTo_eigenImages = { _eigenImages };
     <unknown>[] referenceTo_avgImage = { _avgImage };
     calcEigenObjects(images, /*ref*/ termCrit, /*out*/ referenceTo_eigenImages, /*out*/ referenceTo_avgImage);
     _eigenImages = referenceTo_eigenImages[0];
     _avgImage = referenceTo_avgImage[0];

     /*
         _avgImage.SerializationCompressionRatio = 9;

         foreach (Image<Gray, Single> img in _eigenImages)
             //Set the compression ration to best compression. The serialized object can therefore save spaces
             img.SerializationCompressionRatio = 9;
         */

     _eigenValues = Object[].ConvertAll<Image<Gray, Byte>, Matrix<float>>(images,
         delegate(Image<Gray, Byte> img)
         {
            return new Matrix<float>(eigenDecomposite(img, _eigenImages, _avgImage));
         });

     _labels = labels;

     _eigenDistanceThreshold = eigenDistanceThreshold;
  }

  //>>>>>>>> #region  static methods
  /// <summary>
  /// Caculate the eigen images for the specific traning image
  /// </summary>
  /// <param name="trainingImages">The images used for training </param>
  /// <param name="termCrit">The criteria for tranning</param>
  /// <param name="eigenImages">The resulting eigen images</param>
  /// <param name="avg">The resulting average image</param>
  public static void calcEigenObjects(Image<Gray, Byte>[] trainingImages, /*ref*/ MCvTermCriteria[] termCrit, /*out*/ Image<Gray, Single>[][] eigenImages, /*out*/ Image<Gray, Single>[] avg)
  {
     int width = trainingImages[0].Width;
     int height = trainingImages[0].Height;

     IntPtr[] inObjs = Object[].ConvertAll<Image<Gray, Byte>, IntPtr>(trainingImages, delegate(Image<Gray, Byte> img) { return img.Ptr; });

     if (termCrit[0].max_iter <= 0 || termCrit[0].max_iter > trainingImages.Length)
        termCrit[0].max_iter = trainingImages.Length;
     
     int maxEigenObjs = termCrit[0].max_iter;

     //>>>>>>>> #region  initialize eigen images
     eigenImages[0] = new Image<Gray, float>[maxEigenObjs];
     for (int i = 0; i < eigenImages[0].Length; i++)
        eigenImages[0][i] = new Image<Gray, float>(width, height);
     IntPtr[] eigObjs = Object[].ConvertAll<Image<Gray, Single>, IntPtr>(eigenImages[0], delegate(Image<Gray, Single> img) { return img.Ptr; });
     //<<<<<<<< #endregion 

     avg[0] = new Image<Gray, Single>(width, height);

     CvInvoke.cvCalcEigenObjects(
         inObjs,
         /*ref*/ termCrit,
         eigObjs,
         null,
         avg[0].Ptr);
  }

  /// <summary>
  /// Decompose the image as eigen values, using the specific eigen vectors
  /// </summary>
  /// <param name="src">The image to be decomposed</param>
  /// <param name="eigenImages">The eigen images</param>
  /// <param name="avg">The average images</param>
  /// <returns>Eigen values of the decomposed image</returns>
  public static float[] eigenDecomposite(Image<Gray, Byte> src, Image<Gray, Single>[] eigenImages, Image<Gray, Single> avg)
  {
     return CvInvoke.cvEigenDecomposite(
         src.Ptr,
         Object[].ConvertAll<Image<Gray, Single>, IntPtr>(eigenImages, delegate(Image<Gray, Single> img) { return img.Ptr; }),
         avg.Ptr);
  }
  //<<<<<<<< #endregion 

  /// <summary>
  /// Given the eigen value, reconstruct the projected image
  /// </summary>
  /// <param name="eigenValue">The eigen values</param>
  /// <returns>The projected image</returns>
  public Image<Gray, Byte> eigenProjection(float[] eigenValue)
  {
     Image<Gray, Byte> res = new Image<Gray, byte>(_avgImage.Width, _avgImage.Height);
     CvInvoke.cvEigenProjection(
         Object[].ConvertAll<Image<Gray, Single>, IntPtr>(_eigenImages, delegate(Image<Gray, Single> img) { return img.Ptr; }),
         eigenValue,
         _avgImage.Ptr,
         res.Ptr);
     return res;
  }

  /// <summary>
  /// Get the Euclidean eigen-distance between <paramref name="image"/> and every other image in the database
  /// </summary>
  /// <param name="image">The image to be compared from the training images</param>
  /// <returns>An array of eigen distance from every image in the training images</returns>
  public float[] getEigenDistances(Image<Gray, Byte> image)
  {
     Matrix<float> eigenValue = new Matrix<float>(eigenDecomposite(image, _eigenImages, _avgImage));
     try /*JAVA: was using*/
  	{
        return Object[].ConvertAll<Matrix<float>,Float>(_eigenValues,
            delegate(Matrix<float> eigenValueI)
            {
               return (float)CvInvoke.cvNorm(eigenValue.Ptr, eigenValueI.Ptr, Emgu.CV.CvEnum.NORM_TYPE.CV_L2, IntPtr.Zero);
            });
  	}
     finally { if (eigenValue != null) eigenValue.close(); }
  }

  /// <summary>
  /// Given the <paramref name="image"/> to be examined, find in the database the most similar object, return the index and the eigen distance
  /// </summary>
  /// <param name="image">The image to be searched from the database</param>
  /// <param name="index">The index of the most similar object</param>
  /// <param name="eigenDistance">The eigen distance of the most similar object</param>
  /// <param name="label">The label of the specific image</param>
  public void findMostSimilarObject(Image<Gray, Byte> image, /*out*/ int[] index, /*out*/ float[] eigenDistance, /*out*/ String[] label)
  {
     float[] dist = getEigenDistances(image);

     index[0] = 0;
     eigenDistance[0] = dist[0];
     for (int i = 1; i < dist.length; i++)
     {
        if (dist[i] < eigenDistance[0])
        {
           index[0] = i;
           eigenDistance[0] = dist[i];
        }
     }
     label[0] = getLabels()[index[0]];
  }

  /// <summary>
  /// Try to recognize the image and return its label
  /// </summary>
  /// <param name="image">The image to be recognized</param>
  /// <returns>
  /// String.Empty, if not recognized;
  /// Label of the corresponding image, otherwise
  /// </returns>
  public String recognize(Image<Gray, Byte> image)
  {
     int index = 0;
     float eigenDistance = 0.0f;
     String label = null;
     int[] referenceToIndex = { index };
     float[] referenceToEigenDistance = { eigenDistance };
     String[] referenceToLabel = { label };
     findMostSimilarObject(image, /*out*/ referenceToIndex, /*out*/ referenceToEigenDistance, /*out*/ referenceToLabel);
     index = referenceToIndex[0];
     eigenDistance = referenceToEigenDistance[0];
     label = referenceToLabel[0];

     return (_eigenDistanceThreshold <= 0 || eigenDistance < _eigenDistanceThreshold )  ? _labels[index] : "";
  }
   }

