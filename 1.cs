using System;
using System.Text;
using System.Runtime.InteropServices;

namespace DvdCopyWord.WindowsAPI
{
    class _1
    {
		private string csTestStringVariable;
		public string csPath;

		public TestFunction()
		{

			DirectoryInfo di = new System.IO.DirectoryInfo("Sample path here");;
			DirectoryInfo parent = Directory.GetParent(di.FullName);
			String newPath = Path.Combine(parent.FullName, "sample input path");

			// rename to some temp name, to help change lower and uppercast names
			di.MoveTo(newPath + "__renameTemp__");
			di.MoveTo(newPath);

			// Trying to cleanup to prevent directory locking, doesn't work...
			di = null;
			parent = null;
			GC.Collect(GC.MaxGeneration, GCCollectionMode.Forced);

                        GC.Collect(GC.MaxGeneration, GCCollectionMode.Forced);
                        GC.Collect(GC.MaxGeneration, GCCollectionMode.Forced);
                        GC.Collect(GC.MaxGeneration, GCCollectionMode.Forced);


		}

		private Static Integer TestFunc2()
		{
			///	 test data here....
			return new Integer();
		}

		protected String TestFunc3()
		{
		    return "Helo World";
			//	Lets try this

		}

    }
}
