using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Media;
using System.ComponentModel;
using System.Windows.Controls;
using System.Windows;
using DvdCopyWorld.Common;

using RocketDivision.StarBurnX;
using System.Collections;

namespace DvdCopyWorld.Wizard
{
    public class WizardOptions
    {
        #region Constructor
        public WizardOptions()
        {
            this.Mode = WizardMode.Normal;
            this.OnFinish = null;
            this.IsStartNew = true;
        }
        #endregion

        #region Properties
        /// <summary>
        /// Delegate to execute on wizard finish
        /// </summary>
        public System.Windows.Forms.MethodInvoker OnFinish
        {
            get;
            set;
        }

        /// <summary>
        /// Identifies current wizard running mode
        /// </summary>
        public WizardMode Mode
        {
            get;
            set;
        }

        /// <summary>
        /// Indicate whether to start new wizard or continue previous one.
        /// </summary>
        public bool IsStartNew
        {
            get;
            set;
        }

        #endregion
    }

    /// <summary>
    /// Enum of the current wizard type.
    /// </summary>
    public enum WizardType
    {
        WizardTypeNone = 0,
        Data_Burning,
        Audio_Burning,
        Video_Burning,
        Image_Burning,
        Photo_Burning
    }
    
    /// <summary>
    /// Enum of the current wizard running modes
    /// </summary>
    public enum WizardMode
    {
        Normal,
        Update,
        Copy
    }

    /// <summary>
    /// Enum of the current wizard pages.
    /// </summary>
    public enum WizardPage
    {
        WizardPageNone = 0,
        Go_To_Next = 1,
        Go_To_Main_Page = 2,
        Page_Data_Disc,
        Page_Data_Source_Selection,
        Page_Audio_Disc,
        Page_Video_Disc,
        Page_Video_Source_Selection,
        Page_Burn_Settings,
        Page_Burn_Status
    }

    /// <summary>
    /// Enum of the current button type.
    /// </summary>
    public enum WizardButtonType
    {
        Back = 0x1,
        Next = 0x2,
        StartBurning = 0x4,
        Finish = 0x8,
        Cancel = 0x10
    }

    /// <summary>
    /// Enum of the application burning status.
    /// </summary>
    public enum BurningStatus
    {
        None,
        InProgress,
        Stop,
        Abort,
        Finish,
    }

    public enum TARGET_DISC_RESULT
    {
    };

    public class Delegates
    {
        public delegate void NavigationStatusChanged();
    }

    public interface IWizardPage : INotifyPropertyChanged, IDisposable
    {
        // Is visited.
        bool IsVisited { get; set; }

        // Is data loaded.
        bool IsDataLoaded { get; set; }

        // Current/Next page id.
        WizardPage CurrentPageId { get; }
        WizardPage NextPageID { get; }

        // wizard host interface.
        IWizardHost WizardHost { get; set; }

        // Open, save , verify ....
        bool Open();
        bool Save();
        bool Verify();
    }

    public interface IWizardHost
    {
        // Call back event handled in the main window to navigate to the proper page.
        event System.Windows.Forms.MethodInvoker PageChanged;

        // Wizard modified.
        bool IsWizardModified { get; set; }

        // StarBurn interface.
        IStarBurnX StarBurn { get; }

        // Data burner interface.
        object DataBurner { get; set; }

        Drive SelectedDrive { get; set; }

        // Disc drives speed interface.
        int DiscDriveWriteSpeeds { get; set; }

        // Last error code and error message.
        STARBURN_RESULT_CODES LastErrorCode { get; }
        string LastErrorString { get; }

        void StartBurning(bool isTestMode, string discTitle, string msg1, string msg2);

        // Prefrences interface.
        //IPreferences Preferences { get; }

        // Wizard Pages collection.
        ObservableDictionary<WizardPage, IWizardPage> Pages { get; }

        // Wizard type i.e Data burning, audio burning, video burning etc.
        WizardType WizardType { get; set; }
        
        // Wizard options.
        WizardOptions Options { get; set; }
        
        // To get the current page on which should be navigated...
        IWizardPage CurrentPage { get; }
        
        // To load wizard page.
        void LoadWizardPages();

        // To navigate to given page id.
        void LoadSelectedPage(WizardPage ePageId);

        // To release wizard pages.
        void ReleaseWizardPages();
        
        // TODO: Functions to be decided in future.
        bool ButtonClicked(WizardButtonType buttonType);
        void ActivateHomePage();
        bool EraseDisc();
        bool EjectDisc();

        // Number of copies to burn.
        int NumberOfCopiesToBurn { get; set; }

        // Selected recorder index.
        int SelectedRecorderIndex { get; set; }

        // Disc title.
        string DiscTitle { get; set; }

        // Current session, previous session and Totail disc capacity.
        long CurrentSessionSize { get; set; }   // In kb
        long PreviousSessionSize { get; }  // In kb
        long TotalDiscCapacity { get; }// In kb

        // Refresh drive list.
        bool RefreshDrivesList(ref ComboBox cComboDrives, bool bRefreshDriveList);

        // Is disc available.
        bool IsDiscAvailable();

        // Refresh drive speeds.
        bool RefreshDriveWriteSpeeds(ref ComboBox cComboDrives, bool bRefreshDriveList);

        // Get current recorder.
        Drive GetCurrentRecorder();
    }
        
    public class WizardButton : Button
    {
        public WizardButtonType Type { get; set; }

        // Setting image to show on button
        public ImageSource Image
        {
            get { return (ImageSource)GetValue(ImageProperty); }
            set { SetValue(ImageProperty, value); }
        }

        // DependencyProperty as the backing store for Image.
        public static readonly DependencyProperty ImageProperty =
        DependencyProperty.Register("Image", typeof(ImageSource), typeof(WizardButton));

        // To show image on mouse over and click
        public ImageSource ImageOver
        {
            get { return (ImageSource)GetValue(ImageOverProperty); }
            set { SetValue(ImageOverProperty, value); }
        }

        // Using a DependencyProperty as the backing store for ImageOver.  This enables animation, styling, binding, etc...
        public static readonly DependencyProperty ImageOverProperty =
        DependencyProperty.Register("ImageOver", typeof(ImageSource), typeof(WizardButton));
    }
    
    public class WizardButtonInfo : INotifyPropertyChanged
    {
        string text;
        public string Text
        {
            get
            {
                return text;
            }
            set
            {
                if (value != this.text)
                {
                    text = value;
                    if (PropertyChanged != null)
                        PropertyChanged(this, new PropertyChangedEventArgs("Text"));
                }
            }
        }

        bool isEnabled;

        public bool IsEnabled
        {
            get
            {
                return isEnabled;
            }
            set
            {
                if (value != this.isEnabled)
                {
                    isEnabled = value;
                    if (PropertyChanged != null)
                        PropertyChanged(this, new PropertyChangedEventArgs("IsEnabled"));
                }
            }
        }

        public ToolTip ToolTip
        {
            get;
            set;
        }

        #region INotifyPropertyChanged Members
           public event PropertyChangedEventHandler PropertyChanged;
        #endregion
    }
}
