using System;
using System.Text;
using System.Runtime.InteropServices;

namespace DvdCopyWord.WindowsAPI
{
    public class TestInheritence: ParentTestClass //Sheraz Test1: Inheritance conversion in JAVA
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
			string s= cspath??string.Empty;  //Sheraz Test2: Null operation transaltion in Java
			
			Integer? count= new Integer (); 
			return count;
		}

		protected String TestFunc3()
		{
		    return "Helo World";
			//	Lets try this

		}

    }
}
