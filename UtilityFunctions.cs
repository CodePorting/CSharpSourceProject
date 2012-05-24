using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using DvdCopyWord.WindowsAPI;
using System.IO;
using System.Runtime.InteropServices;


namespace DvdCopyWorld.Common
{
    class UtilityFunctions
    {
        public const long kiKB = 1024;
        public const long kiMB = 1024 * kiKB;
        public const long kiGB = 1024 * kiMB;

        /// <summary>
        /// To get size in KB, MB, GB.
        /// </summary>
        /// <param name="ulSizeInBytes"></param>
        /// <returns></returns>
        static public string FormatSize(ulong ulSizeInBytes)
        {
            string csDestination;
            if (ulSizeInBytes > 0)
            {

                if (ulSizeInBytes >= kiGB)
                {
                    double dGBSize = (double)ulSizeInBytes / kiGB;
                    csDestination = String.Format(("{0:F0}"), dGBSize);
                    csDestination = csDestination + " GB";
                }
                else if (ulSizeInBytes >= kiMB)
                {
                    double dMBSize = (double)ulSizeInBytes / kiMB;
                    csDestination = String.Format(("{0:F0}"), dMBSize);
                    csDestination = csDestination + " MB";
                }
                else if (ulSizeInBytes > kiKB)
                {
                    csDestination = String.Format(("{0:F0}"), ulSizeInBytes / kiKB);
                    csDestination = csDestination + " KB";
                }
                else
                {
                    csDestination = "1 KB";
                }
            }
            else
            {
                csDestination = "0 KB";
            }

            return csDestination;
        }

        /// <summary>
        /// Get type and icon associated with given file/directory
        /// </summary>
        /// 
        static public bool GetFileInfo(string path, FileAttributes attr, ref ImageSource imageSource, ref string type)
        {
            ShellAPI.SHFILEINFO shellInfo = new ShellAPI.SHFILEINFO();
            ShellAPI.SHGFI vFlags =
                ShellAPI.SHGFI.SHGFI_SMALLICON |
                ShellAPI.SHGFI.SHGFI_ICON |
                ShellAPI.SHGFI.SHGFI_USEFILEATTRIBUTES |
                ShellAPI.SHGFI.SHGFI_DISPLAYNAME |
                ShellAPI.SHGFI.SHGFI_TYPENAME;
            ShellAPI.SHGetFileInfo(path, (uint)attr, ref shellInfo, (uint)Marshal.SizeOf(shellInfo), vFlags);

            // (1) Type
            type = shellInfo.szTypeName;

            // (2) Image Source.
            imageSource = null;
            if (shellInfo.hIcon != null)
            {
                try
                {
                    MemoryStream memStream = new MemoryStream();
                    ((System.Drawing.Bitmap)System.Drawing.Icon.FromHandle(shellInfo.hIcon).ToBitmap()).
                        Save(memStream, System.Drawing.Imaging.ImageFormat.Png);
                    memStream.Seek(0, SeekOrigin.Begin);
                    PngBitmapDecoder pngBmpDecoder = new PngBitmapDecoder(memStream,
                         BitmapCreateOptions.PreservePixelFormat,
                          BitmapCacheOption.Default);
                    imageSource = pngBmpDecoder.Frames[0];
                    //User32API.DestroyIcon(shellInfo.hIcon);
                }
                catch (Exception exp)
                {
                    //Debug.Assert(false, exp.Message);
                }

                return true;
            }
            return false;
        }

        /// <summary>
        /// To get the directory size including all files.
        /// </summary>
        /// <param name="csPath"></param>
        /// <returns></returns>
        static public ulong GetDirectorySize(string csPath)
        {
            ulong ulSize = 0;
            string [] dirFiles = System.IO.Directory.GetFiles(csPath);

            foreach (string csFile in dirFiles)
            {
                FileInfo f = new FileInfo(csFile);
                ulSize += (ulong)f.Length;
            }
            return ulSize;
        }

        /// <summary>
        /// To get the formatted duration string.
        /// </summary>
        /// <param name="pSeconds"></param>
        /// <returns></returns>
        static public string FormatDurationTime(long pSeconds)
        {
            if (pSeconds <= 0) { return " 00:00 "; }
            if (pSeconds < 60) { return " 00:" + pSeconds + " "; }

            long sec;
            long min;
            long hour;

            if (pSeconds < 3600)
            {
                min = (pSeconds / 60);
                sec = pSeconds - min * 60;

                return String.Format(" {0:00}:{1:00} ", min, sec);
            }
            long usedSecs = 0;
            hour = pSeconds / 60 / 60;
            usedSecs += hour * 60 * 60;

            min = (pSeconds - usedSecs) / 60;
            usedSecs = usedSecs + min * 60;

            sec = pSeconds - usedSecs;
            return String.Format(" {0:00}:{1:00}:{2:00} ", hour, min, sec);
        }
    }
}
