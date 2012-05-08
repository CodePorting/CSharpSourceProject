package DvdCopyWord.WindowsAPI;

// ********* THIS FILE IS AUTO PORTED FORM C# USING CODEPORTING.COM *********

import com.codeporting.csharp2java.java.Enum;
import com.codeporting.csharp2java.System.msObject;
import com.codeporting.csharp2java.System.Guid;
import com.codeporting.csharp2java.System.FlagsAttribute;
import com.codeporting.csharp2java.java.Struct;

///////////////////////////////////////////////////////////////////////
// Change for test commit.
///////////////////////////////////////////////////////////////////////


class ShellAPI
{
    //>>>>>>>> #region  Constants

    public static final int FILE_ATTRIBUTE_NORMAL = 0x80;
    public static Guid IID_IShellFolder = new Guid("000214E6-0000-0000-C000-000000000046");

    //<<<<<<<< #endregion 

    //>>>>>>>> #region  DLL Imports

    @DllImport ("user32.dll")
    public static extern int sendMessage(IntPtr pWnd, /*uint*/long uMsg, /*uint*/long wParam, IntPtr lParam);

    @DllImport ("shell32.dll")
    public static extern int sHGetDesktopFolder(/*ref*/ IShellFolder[] ppshf);

	// Used for unicode and Ansi strings as it is from shell.
    @DllImport ("shell32.dll",charSet = CharSet.Auto)
    public static extern IntPtr sHGetFileInfo(String pszPath, /*uint*/long dwFileAttribs, /*ref*/ SHFILEINFO[] psfi, /*uint*/long cbFileInfo, /*SHGFI*/int uFlags);

	// Used for unicode and Ansi strings as it is from shell.
    @DllImport ("shell32.dll",charSet = CharSet.Auto)
    public static extern IntPtr sHGetFileInfo(IntPtr pIDL, /*uint*/long dwFileAttributes, /*ref*/ SHFILEINFO[] psfi, /*uint*/long cbFileInfo, /*SHGFI*/int uFlags);

    @DllImport ("shell32.dll")
    public static extern int sHGetSpecialFolderLocation(IntPtr hwndOwner, /*CSIDL*/long nFolder, /*ref*/ IntPtr[] ppidl);

    @DllImport ("shell32.dll")
    public static extern IntPtr iLCombine(IntPtr pIDLParent, IntPtr pIDLChild);

    //  Used for unicode and Ansi strings as it is from shell.
    @DllImport ("shell32.dll",charSet = CharSet.Auto)
    public static extern int sHGetPathFromIDList(IntPtr pIDL, StringBuilder strPath);

    @DllImport ("shell32.dll")
    public static extern IntPtr shellExecute(
        IntPtr hwnd,
        String lpOperation,
        String lpFile,
        String lpParameters,
        String lpDirectory,
        /*ShowCommands*/int nShowCmd);

    //<<<<<<<< #endregion 

    //>>>>>>>> #region  Enumerations

    @FlagsAttribute
    public static /*enum*/ final class SHGNO /*: uint*/ extends Enum
    {
	private SHGNO(){}	
        public static final /*uint*/long SHGDN_NORMAL = 0x0000;                 // Default (display purpose)
        public static final /*uint*/long SHGDN_INFOLDER = 0x0001;               // Displayed under a folder (relative)
        public static final /*uint*/long SHGDN_FOREDITING = 0x1000;             // For in-place editing
        public static final /*uint*/long SHGDN_FORADDRESSBAR = 0x4000;          // UI friendly parsing name (remove ugly stuff)
        public static final /*uint*/long SHGDN_FORPARSING = 0x8000;

	static {
		Enum.register(new Enum.FlaggedEnum(SHGNO.class, Long.class) {{
		addConstant("SHGDN_NORMAL", SHGDN_NORMAL);
		addConstant("SHGDN_INFOLDER", SHGDN_INFOLDER);
		addConstant("SHGDN_FOREDITING", SHGDN_FOREDITING);
		addConstant("SHGDN_FORADDRESSBAR", SHGDN_FORADDRESSBAR);
		addConstant("SHGDN_FORPARSING", SHGDN_FORPARSING);
		}});
	}

             // Parsing name for ParseDisplayName()
    }

    @FlagsAttribute
    public static /*enum*/ final class SHCONTF /*: uint*/ extends Enum
    {
	private SHCONTF(){}	
        public static final /*uint*/long SHCONTF_FOLDERS = 0x0020;              // Only want folders enumerated (SFGAO_FOLDER)
        public static final /*uint*/long SHCONTF_NONFOLDERS = 0x0040;           // Include non folders
        public static final /*uint*/long SHCONTF_INCLUDEHIDDEN = 0x0080;        // Show items normally hidden
        public static final /*uint*/long SHCONTF_INIT_ON_FIRST_NEXT = 0x0100;   // Allow EnumObject() to return before validating enum
        public static final /*uint*/long SHCONTF_NETPRINTERSRCH = 0x0200;       // Hint that client is looking for printers
        public static final /*uint*/long SHCONTF_SHAREABLE = 0x0400;            // Hint that client is looking sharable resources (remote shares)
        public static final /*uint*/long SHCONTF_STORAGE = 0x0800;              // Include all items with accessible storage and their ancestors
        public static final /*uint*/long SHCONTF_FASTITEMS=0x2000;

	static {
		Enum.register(new Enum.FlaggedEnum(SHCONTF.class, Long.class) {{
		addConstant("SHCONTF_FOLDERS", SHCONTF_FOLDERS);
		addConstant("SHCONTF_NONFOLDERS", SHCONTF_NONFOLDERS);
		addConstant("SHCONTF_INCLUDEHIDDEN", SHCONTF_INCLUDEHIDDEN);
		addConstant("SHCONTF_INIT_ON_FIRST_NEXT", SHCONTF_INIT_ON_FIRST_NEXT);
		addConstant("SHCONTF_NETPRINTERSRCH", SHCONTF_NETPRINTERSRCH);
		addConstant("SHCONTF_SHAREABLE", SHCONTF_SHAREABLE);
		addConstant("SHCONTF_STORAGE", SHCONTF_STORAGE);
		addConstant("SHCONTF_FASTITEMS", SHCONTF_FASTITEMS);
		}});
	}


    }

    @FlagsAttribute
    public static /*enum*/ final class SFGAOF /*: uint*/ extends Enum
    {
	private SFGAOF(){}	
        public static final /*uint*/long SFGAO_CANCOPY = 0x1;                   // Objects can be copied  (DROPEFFECT_COPY)
        public static final /*uint*/long SFGAO_CANMOVE = 0x2;                   // Objects can be moved   (DROPEFFECT_MOVE)
        public static final /*uint*/long SFGAO_CANLINK = 0x4;                   // Objects can be linked  (DROPEFFECT_LINK)
        public static final /*uint*/long SFGAO_STORAGE = 0x00000008;            // Supports BindToObject(IID_IStorage)
        public static final /*uint*/long SFGAO_CANRENAME = 0x00000010;          // Objects can be renamed
        public static final /*uint*/long SFGAO_CANDELETE = 0x00000020;          // Objects can be deleted
        public static final /*uint*/long SFGAO_HASPROPSHEET = 0x00000040;       // Objects have property sheets
        public static final /*uint*/long SFGAO_DROPTARGET = 0x00000100;         // Objects are drop target
        public static final /*uint*/long SFGAO_CAPABILITYMASK = 0x00000177;
        public static final /*uint*/long SFGAO_ENCRYPTED = 0x00002000;          // Object is encrypted (use alt color)
        public static final /*uint*/long SFGAO_ISSLOW = 0x00004000;             // 'Slow' object
        public static final /*uint*/long SFGAO_GHOSTED = 0x00008000;            // Ghosted icon
        public static final /*uint*/long SFGAO_LINK = 0x00010000;               // Shortcut (link)
        public static final /*uint*/long SFGAO_SHARE = 0x00020000;              // Shared
        public static final /*uint*/long SFGAO_READONLY = 0x00040000;           // Read-only
        public static final /*uint*/long SFGAO_HIDDEN = 0x00080000;             // Hidden object
        public static final /*uint*/long SFGAO_DISPLAYATTRMASK = 0x000FC000;
        public static final /*uint*/long SFGAO_FILESYSANCESTOR = 0x10000000;    // May contain children with SFGAO_FILESYSTEM
        public static final /*uint*/long SFGAO_FOLDER = 0x20000000;             // Support BindToObject(IID_IShellFolder)
        public static final /*uint*/long SFGAO_FILESYSTEM = 0x40000000;         // Is a win32 file system object (file/folder/root)
        public static final /*uint*/long SFGAO_HASSUBFOLDER = 0x80000000L;       // May contain children with SFGAO_FOLDER
        public static final /*uint*/long SFGAO_CONTENTSMASK = 0x80000000L;
        public static final /*uint*/long SFGAO_VALIDATE = 0x01000000;           // Invalidate cached information
        public static final /*uint*/long SFGAO_REMOVABLE = 0x02000000;          // Is this removeable media?
        public static final /*uint*/long SFGAO_COMPRESSED = 0x04000000;         // Object is compressed (use alt color)
        public static final /*uint*/long SFGAO_BROWSABLE = 0x08000000;          // Supports IShellFolder, but only implements CreateViewObject() (non-folder view)
        public static final /*uint*/long SFGAO_NONENUMERATED = 0x00100000;      // Is a non-enumerated object
        public static final /*uint*/long SFGAO_NEWCONTENT = 0x00200000;         // Should show bold in explorer tree
        public static final /*uint*/long SFGAO_CANMONIKER = 0x00400000;         // Defunct
        public static final /*uint*/long SFGAO_HASSTORAGE = 0x00400000;         // Defunct
        public static final /*uint*/long SFGAO_STREAM = 0x00400000;             // Supports BindToObject(IID_IStream)
        public static final /*uint*/long SFGAO_STORAGEANCESTOR = 0x00800000;    // May contain children with SFGAO_STORAGE or SFGAO_STREAM
        public static final /*uint*/long SFGAO_STORAGECAPMASK = 0x70C50008;     // For determining storage capabilities, ie for open/save semantics            

        public static final /*uint*/long SFGAO_ALL=0xFFFFFFFFL;

	static {
		Enum.register(new Enum.FlaggedEnum(SFGAOF.class, Long.class) {{
		addConstant("SFGAO_CANCOPY", SFGAO_CANCOPY);
		addConstant("SFGAO_CANMOVE", SFGAO_CANMOVE);
		addConstant("SFGAO_CANLINK", SFGAO_CANLINK);
		addConstant("SFGAO_STORAGE", SFGAO_STORAGE);
		addConstant("SFGAO_CANRENAME", SFGAO_CANRENAME);
		addConstant("SFGAO_CANDELETE", SFGAO_CANDELETE);
		addConstant("SFGAO_HASPROPSHEET", SFGAO_HASPROPSHEET);
		addConstant("SFGAO_DROPTARGET", SFGAO_DROPTARGET);
		addConstant("SFGAO_CAPABILITYMASK", SFGAO_CAPABILITYMASK);
		addConstant("SFGAO_ENCRYPTED", SFGAO_ENCRYPTED);
		addConstant("SFGAO_ISSLOW", SFGAO_ISSLOW);
		addConstant("SFGAO_GHOSTED", SFGAO_GHOSTED);
		addConstant("SFGAO_LINK", SFGAO_LINK);
		addConstant("SFGAO_SHARE", SFGAO_SHARE);
		addConstant("SFGAO_READONLY", SFGAO_READONLY);
		addConstant("SFGAO_HIDDEN", SFGAO_HIDDEN);
		addConstant("SFGAO_DISPLAYATTRMASK", SFGAO_DISPLAYATTRMASK);
		addConstant("SFGAO_FILESYSANCESTOR", SFGAO_FILESYSANCESTOR);
		addConstant("SFGAO_FOLDER", SFGAO_FOLDER);
		addConstant("SFGAO_FILESYSTEM", SFGAO_FILESYSTEM);
		addConstant("SFGAO_HASSUBFOLDER", SFGAO_HASSUBFOLDER);
		addConstant("SFGAO_CONTENTSMASK", SFGAO_CONTENTSMASK);
		addConstant("SFGAO_VALIDATE", SFGAO_VALIDATE);
		addConstant("SFGAO_REMOVABLE", SFGAO_REMOVABLE);
		addConstant("SFGAO_COMPRESSED", SFGAO_COMPRESSED);
		addConstant("SFGAO_BROWSABLE", SFGAO_BROWSABLE);
		addConstant("SFGAO_NONENUMERATED", SFGAO_NONENUMERATED);
		addConstant("SFGAO_NEWCONTENT", SFGAO_NEWCONTENT);
		addConstant("SFGAO_CANMONIKER", SFGAO_CANMONIKER);
		addConstant("SFGAO_HASSTORAGE", SFGAO_HASSTORAGE);
		addConstant("SFGAO_STREAM", SFGAO_STREAM);
		addConstant("SFGAO_STORAGEANCESTOR", SFGAO_STORAGEANCESTOR);
		addConstant("SFGAO_STORAGECAPMASK", SFGAO_STORAGECAPMASK);
		addConstant("SFGAO_ALL", SFGAO_ALL);
		}});
	}


    }

    @FlagsAttribute
    public static /*enum*/ final class STRRET /*: uint*/ extends Enum
    {
	private STRRET(){}	
        public static final /*uint*/long STRRET_WSTR = 0;
        public static final /*uint*/long STRRET_OFFSET = 0x1;   
        public static final /*uint*/long STRRET_CSTR = 0x2;

	static {
		Enum.register(new Enum.FlaggedEnum(STRRET.class, Long.class) {{
		addConstant("STRRET_WSTR", STRRET_WSTR);
		addConstant("STRRET_OFFSET", STRRET_OFFSET);
		addConstant("STRRET_CSTR", STRRET_CSTR);
		}});
	}

  
    }

    @FlagsAttribute
    public static /*enum*/ final class SHGFI extends Enum
    {
	private SHGFI(){}	
        public static final int SHGFI_ICON = 0x000000100;
        public static final int SHGFI_DISPLAYNAME = 0x000000200;
        public static final int SHGFI_TYPENAME = 0x000000400;
        public static final int SHGFI_ATTRIBUTES = 0x000000800;
        public static final int SHGFI_ICONLOCATION = 0x000001000;
        public static final int SHGFI_EXETYPE = 0x000002000;
        public static final int SHGFI_SYSICONINDEX = 0x000004000;
        public static final int SHGFI_LINKOVERLAY = 0x000008000;
        public static final int SHGFI_SELECTED = 0x000010000;
        public static final int SHGFI_ATTR_SPECIFIED = 0x000020000;
        public static final int SHGFI_LARGEICON = 0x000000000;
        public static final int SHGFI_SMALLICON = 0x000000001;
        public static final int SHGFI_OPENICON = 0x000000002;
        public static final int SHGFI_SHELLICONSIZE = 0x000000004;
        public static final int SHGFI_PIDL = 0x000000008;
        public static final int SHGFI_USEFILEATTRIBUTES = 0x000000010;
        public static final int SHGFI_ADDOVERLAYS = 0x000000020;
        public static final int SHGFI_OVERLAYINDEX = 0x000000040;

	static {
		Enum.register(new Enum.FlaggedEnum(SHGFI.class, Integer.class) {{
		addConstant("SHGFI_ICON", SHGFI_ICON);
		addConstant("SHGFI_DISPLAYNAME", SHGFI_DISPLAYNAME);
		addConstant("SHGFI_TYPENAME", SHGFI_TYPENAME);
		addConstant("SHGFI_ATTRIBUTES", SHGFI_ATTRIBUTES);
		addConstant("SHGFI_ICONLOCATION", SHGFI_ICONLOCATION);
		addConstant("SHGFI_EXETYPE", SHGFI_EXETYPE);
		addConstant("SHGFI_SYSICONINDEX", SHGFI_SYSICONINDEX);
		addConstant("SHGFI_LINKOVERLAY", SHGFI_LINKOVERLAY);
		addConstant("SHGFI_SELECTED", SHGFI_SELECTED);
		addConstant("SHGFI_ATTR_SPECIFIED", SHGFI_ATTR_SPECIFIED);
		addConstant("SHGFI_LARGEICON", SHGFI_LARGEICON);
		addConstant("SHGFI_SMALLICON", SHGFI_SMALLICON);
		addConstant("SHGFI_OPENICON", SHGFI_OPENICON);
		addConstant("SHGFI_SHELLICONSIZE", SHGFI_SHELLICONSIZE);
		addConstant("SHGFI_PIDL", SHGFI_PIDL);
		addConstant("SHGFI_USEFILEATTRIBUTES", SHGFI_USEFILEATTRIBUTES);
		addConstant("SHGFI_ADDOVERLAYS", SHGFI_ADDOVERLAYS);
		addConstant("SHGFI_OVERLAYINDEX", SHGFI_OVERLAYINDEX);
		}});
	}


    }

    @FlagsAttribute
    public static /*enum*/ final class CSIDL/*: uint*/ extends Enum
    {
	private CSIDL(){}	
        public static final /*uint*/long CSIDL_DESKTOP = 0x0000;    // <desktop>
        public static final /*uint*/long CSIDL_INTERNET = 0x0001;    // Internet Explorer (icon on desktop)
        public static final /*uint*/long CSIDL_PROGRAMS = 0x0002;    // Start Menu\Programs
        public static final /*uint*/long CSIDL_CONTROLS = 0x0003;    // My Computer\Control Panel
        public static final /*uint*/long CSIDL_PRINTERS = 0x0004;    // My Computer\Printers
        public static final /*uint*/long CSIDL_PERSONAL = 0x0005;    // My Documents
        public static final /*uint*/long CSIDL_FAVORITES = 0x0006;    // <user name>\Favorites
        public static final /*uint*/long CSIDL_STARTUP = 0x0007;    // Start Menu\Programs\Startup
        public static final /*uint*/long CSIDL_RECENT = 0x0008;    // <user name>\Recent
        public static final /*uint*/long CSIDL_SENDTO = 0x0009;    // <user name>\SendTo
        public static final /*uint*/long CSIDL_BITBUCKET = 0x000a;    // <desktop>\Recycle Bin
        public static final /*uint*/long CSIDL_STARTMENU = 0x000b;    // <user name>\Start Menu
        public static final /*uint*/long CSIDL_MYDOCUMENTS = 0x000c;    // logical "My Documents" desktop icon
        public static final /*uint*/long CSIDL_MYMUSIC = 0x000d;    // "My Music" folder
        public static final /*uint*/long CSIDL_MYVIDEO = 0x000e;    // "My Videos" folder
        public static final /*uint*/long CSIDL_DESKTOPDIRECTORY = 0x0010;    // <user name>\Desktop
        public static final /*uint*/long CSIDL_DRIVES = 0x0011;    // My Computer
        public static final /*uint*/long CSIDL_NETWORK = 0x0012;    // Network Neighborhood (My Network Places)
        public static final /*uint*/long CSIDL_NETHOOD = 0x0013;    // <user name>\nethood
        public static final /*uint*/long CSIDL_FONTS = 0x0014;    // windows\fonts
        public static final /*uint*/long CSIDL_TEMPLATES = 0x0015;
        public static final /*uint*/long CSIDL_COMMON_STARTMENU = 0x0016;    // All Users\Start Menu
        public static final /*uint*/long CSIDL_COMMON_PROGRAMS = 0X0017;    // All Users\Start Menu\Programs
        public static final /*uint*/long CSIDL_COMMON_STARTUP = 0x0018;    // All Users\Startup
        public static final /*uint*/long CSIDL_COMMON_DESKTOPDIRECTORY = 0x0019;    // All Users\Desktop
        public static final /*uint*/long CSIDL_APPDATA = 0x001a;    // <user name>\Application Data
        public static final /*uint*/long CSIDL_PRINTHOOD = 0x001b;    // <user name>\PrintHood

        public static final /*uint*/long CSIDL_LOCAL_APPDATA = 0x001c;    // <user name>\Local Settings\Applicaiton Data (non roaming)

        public static final /*uint*/long CSIDL_ALTSTARTUP = 0x001d;    // non localized startup
        public static final /*uint*/long CSIDL_COMMON_ALTSTARTUP = 0x001e;    // non localized common startup
        public static final /*uint*/long CSIDL_COMMON_FAVORITES = 0x001f;

        public static final /*uint*/long CSIDL_INTERNET_CACHE = 0x0020;
        public static final /*uint*/long CSIDL_COOKIES = 0x0021;
        public static final /*uint*/long CSIDL_HISTORY = 0x0022;
        public static final /*uint*/long CSIDL_COMMON_APPDATA = 0x0023;    // All Users\Application Data
        public static final /*uint*/long CSIDL_WINDOWS = 0x0024;    // GetWindowsDirectory()
        public static final /*uint*/long CSIDL_SYSTEM = 0x0025;    // GetSystemDirectory()
        public static final /*uint*/long CSIDL_PROGRAM_FILES = 0x0026;    // C:\Program Files
        public static final /*uint*/long CSIDL_MYPICTURES = 0x0027;    // C:\Program Files\My Pictures

        public static final /*uint*/long CSIDL_PROFILE = 0x0028;    // USERPROFILE
        public static final /*uint*/long CSIDL_SYSTEMX_86 = 0x0029;    // x86 system directory on RISC
        public static final /*uint*/long CSIDL_PROGRAM_FILESX_86 = 0x002a;    // x86 C:\Program Files on RISC

        public static final /*uint*/long CSIDL_PROGRAM_FILES_COMMON = 0x002b;    // C:\Program Files\Common

        public static final /*uint*/long CSIDL_PROGRAM_FILES_COMMONX_86 = 0x002c;    // x86 Program Files\Common on RISC
        public static final /*uint*/long CSIDL_COMMON_TEMPLATES = 0x002d;    // All Users\Templates

        public static final /*uint*/long CSIDL_COMMON_DOCUMENTS = 0x002e;    // All Users\Documents
        public static final /*uint*/long CSIDL_COMMON_ADMINTOOLS = 0x002f;    // All Users\Start Menu\Programs\Administrative Tools
        public static final /*uint*/long CSIDL_ADMINTOOLS = 0x0030;    // <user name>\Start Menu\Programs\Administrative Tools

        public static final /*uint*/long CSIDL_CONNECTIONS = 0x0031;    // Network and Dial-up Connections
        public static final /*uint*/long CSIDL_COMMON_MUSIC = 0x0035;    // All Users\My Music
        public static final /*uint*/long CSIDL_COMMON_PICTURES = 0x0036;    // All Users\My Pictures
        public static final /*uint*/long CSIDL_COMMON_VIDEO = 0x0037;    // All Users\My Video

        public static final /*uint*/long CSIDL_CDBURN_AREA = 0x003b;

	static {
		Enum.register(new Enum.FlaggedEnum(CSIDL.class, Long.class) {{
		addConstant("CSIDL_DESKTOP", CSIDL_DESKTOP);
		addConstant("CSIDL_INTERNET", CSIDL_INTERNET);
		addConstant("CSIDL_PROGRAMS", CSIDL_PROGRAMS);
		addConstant("CSIDL_CONTROLS", CSIDL_CONTROLS);
		addConstant("CSIDL_PRINTERS", CSIDL_PRINTERS);
		addConstant("CSIDL_PERSONAL", CSIDL_PERSONAL);
		addConstant("CSIDL_FAVORITES", CSIDL_FAVORITES);
		addConstant("CSIDL_STARTUP", CSIDL_STARTUP);
		addConstant("CSIDL_RECENT", CSIDL_RECENT);
		addConstant("CSIDL_SENDTO", CSIDL_SENDTO);
		addConstant("CSIDL_BITBUCKET", CSIDL_BITBUCKET);
		addConstant("CSIDL_STARTMENU", CSIDL_STARTMENU);
		addConstant("CSIDL_MYDOCUMENTS", CSIDL_MYDOCUMENTS);
		addConstant("CSIDL_MYMUSIC", CSIDL_MYMUSIC);
		addConstant("CSIDL_MYVIDEO", CSIDL_MYVIDEO);
		addConstant("CSIDL_DESKTOPDIRECTORY", CSIDL_DESKTOPDIRECTORY);
		addConstant("CSIDL_DRIVES", CSIDL_DRIVES);
		addConstant("CSIDL_NETWORK", CSIDL_NETWORK);
		addConstant("CSIDL_NETHOOD", CSIDL_NETHOOD);
		addConstant("CSIDL_FONTS", CSIDL_FONTS);
		addConstant("CSIDL_TEMPLATES", CSIDL_TEMPLATES);
		addConstant("CSIDL_COMMON_STARTMENU", CSIDL_COMMON_STARTMENU);
		addConstant("CSIDL_COMMON_PROGRAMS", CSIDL_COMMON_PROGRAMS);
		addConstant("CSIDL_COMMON_STARTUP", CSIDL_COMMON_STARTUP);
		addConstant("CSIDL_COMMON_DESKTOPDIRECTORY", CSIDL_COMMON_DESKTOPDIRECTORY);
		addConstant("CSIDL_APPDATA", CSIDL_APPDATA);
		addConstant("CSIDL_PRINTHOOD", CSIDL_PRINTHOOD);
		addConstant("CSIDL_LOCAL_APPDATA", CSIDL_LOCAL_APPDATA);
		addConstant("CSIDL_ALTSTARTUP", CSIDL_ALTSTARTUP);
		addConstant("CSIDL_COMMON_ALTSTARTUP", CSIDL_COMMON_ALTSTARTUP);
		addConstant("CSIDL_COMMON_FAVORITES", CSIDL_COMMON_FAVORITES);
		addConstant("CSIDL_INTERNET_CACHE", CSIDL_INTERNET_CACHE);
		addConstant("CSIDL_COOKIES", CSIDL_COOKIES);
		addConstant("CSIDL_HISTORY", CSIDL_HISTORY);
		addConstant("CSIDL_COMMON_APPDATA", CSIDL_COMMON_APPDATA);
		addConstant("CSIDL_WINDOWS", CSIDL_WINDOWS);
		addConstant("CSIDL_SYSTEM", CSIDL_SYSTEM);
		addConstant("CSIDL_PROGRAM_FILES", CSIDL_PROGRAM_FILES);
		addConstant("CSIDL_MYPICTURES", CSIDL_MYPICTURES);
		addConstant("CSIDL_PROFILE", CSIDL_PROFILE);
		addConstant("CSIDL_SYSTEMX_86", CSIDL_SYSTEMX_86);
		addConstant("CSIDL_PROGRAM_FILESX_86", CSIDL_PROGRAM_FILESX_86);
		addConstant("CSIDL_PROGRAM_FILES_COMMON", CSIDL_PROGRAM_FILES_COMMON);
		addConstant("CSIDL_PROGRAM_FILES_COMMONX_86", CSIDL_PROGRAM_FILES_COMMONX_86);
		addConstant("CSIDL_COMMON_TEMPLATES", CSIDL_COMMON_TEMPLATES);
		addConstant("CSIDL_COMMON_DOCUMENTS", CSIDL_COMMON_DOCUMENTS);
		addConstant("CSIDL_COMMON_ADMINTOOLS", CSIDL_COMMON_ADMINTOOLS);
		addConstant("CSIDL_ADMINTOOLS", CSIDL_ADMINTOOLS);
		addConstant("CSIDL_CONNECTIONS", CSIDL_CONNECTIONS);
		addConstant("CSIDL_COMMON_MUSIC", CSIDL_COMMON_MUSIC);
		addConstant("CSIDL_COMMON_PICTURES", CSIDL_COMMON_PICTURES);
		addConstant("CSIDL_COMMON_VIDEO", CSIDL_COMMON_VIDEO);
		addConstant("CSIDL_CDBURN_AREA", CSIDL_CDBURN_AREA);
		}});
	}

    // USERPROFILE\Local Settings\Application Data\Microsoft\CD Burning
    }

    public static /*enum*/ final class ShowCommands /*: int*/ extends Enum
    {
	private ShowCommands(){}	
        public static final int SW_HIDE = 0;
        public static final int SW_SHOWNORMAL = 1;
        public static final int SW_NORMAL = 1;
        public static final int SW_SHOWMINIMIZED = 2;
        public static final int SW_SHOWMAXIMIZED = 3;
        public static final int SW_MAXIMIZE = 3;
        public static final int SW_SHOWNOACTIVATE = 4;
        public static final int SW_SHOW = 5;
        public static final int SW_MINIMIZE = 6;
        public static final int SW_SHOWMINNOACTIVE = 7;
        public static final int SW_SHOWNA = 8;
        public static final int SW_RESTORE = 9;
        public static final int SW_SHOWDEFAULT = 10;
        public static final int SW_FORCEMINIMIZE = 11;
        public static final int SW_MAX = 11;

	static {
		Enum.register(new Enum.SimpleEnum(ShowCommands.class, Integer.class) {{
		addConstant("SW_HIDE", SW_HIDE);
		addConstant("SW_SHOWNORMAL", SW_SHOWNORMAL);
		addConstant("SW_NORMAL", SW_NORMAL);
		addConstant("SW_SHOWMINIMIZED", SW_SHOWMINIMIZED);
		addConstant("SW_SHOWMAXIMIZED", SW_SHOWMAXIMIZED);
		addConstant("SW_MAXIMIZE", SW_MAXIMIZE);
		addConstant("SW_SHOWNOACTIVATE", SW_SHOWNOACTIVATE);
		addConstant("SW_SHOW", SW_SHOW);
		addConstant("SW_MINIMIZE", SW_MINIMIZE);
		addConstant("SW_SHOWMINNOACTIVE", SW_SHOWMINNOACTIVE);
		addConstant("SW_SHOWNA", SW_SHOWNA);
		addConstant("SW_RESTORE", SW_RESTORE);
		addConstant("SW_SHOWDEFAULT", SW_SHOWDEFAULT);
		addConstant("SW_FORCEMINIMIZE", SW_FORCEMINIMIZE);
		addConstant("SW_MAX", SW_MAX);
		}});
	}


    }

    //<<<<<<<< #endregion 

    //>>>>>>>> #region  Structures

	//  Used for unicode and Ansi strings as it is from shell.
    @StructLayout (LayoutKind.Sequential,charSet = CharSet.Auto)
    public static /*struct*/ class SHFILEINFO extends  Struct<SHFILEINFO>
    {
    	public SHFILEINFO(){}
    	
        public IntPtr hIcon;
        public int iIcon;
        public /*uint*/long dwAttributes;
        @MarshalAs (UnmanagedType.ByValTStr,sizeConst = 260)
        public String szDisplayName;
        @MarshalAs (UnmanagedType.ByValTStr,sizeConst = 80)
        public String szTypeName;

    //JAVA-added structs clone 

    public void CloneTo(SHFILEINFO that)
    {
        that.hIcon = hIcon;
        that.iIcon = iIcon;
        that.dwAttributes = dwAttributes;
        that.szDisplayName = szDisplayName;
        that.szTypeName = szTypeName;
    }

    public SHFILEINFO Clone() 
    { 
        SHFILEINFO struct = new SHFILEINFO(); 
        CloneTo(struct); 
        return struct; 
    }

    public Object clone() 
    { 
        return Clone(); 
    }


    //JAVA-added Equality members 

    private boolean EqualsByValue(SHFILEINFO that)
    {
        return msObject.equals(that.hIcon, hIcon) && that.iIcon == iIcon && that.dwAttributes == dwAttributes && msObject.equals(that.szDisplayName, szDisplayName) && msObject.equals(that.szTypeName, szTypeName);
    }

    public boolean equals(Object obj) 
    { 
        assert obj != null; 
        if (msObject.referenceEquals(null, obj)) return false; 
        if (msObject.referenceEquals(this, obj)) return true; 
        if (!(obj instanceof SHFILEINFO)) return false; 
        return EqualsByValue((SHFILEINFO)obj); 
    } 
    
    public static boolean equals(SHFILEINFO obj1, SHFILEINFO obj2) 
    { 
        return obj1.equals(obj2); 
    }

    }

    //<<<<<<<< #endregion 

    //>>>>>>>> #region  Interfaces

    @ComImport
    @InterfaceType (ComInterfaceType.InterfaceIsIUnknown)
    @Guid ("000214E6-0000-0000-C000-000000000046")
    public interface IShellFolder
    {
        // Translates a file object's or folder's display name into an item identifier list.
        // Return value: error code, if any
        @PreserveSig ()
        public /*uint*/long parseDisplayName(
            IntPtr hwnd,             // Optional window handle
            IntPtr pbc,              // Optional bind context that controls the parsing operation. This parameter is normally set to NULL. 
            @In () @MarshalAs (UnmanagedType.LPWStr) 
            String pszDisplayName,   // Null-terminated UNICODE string with the display name.
            /*out*/ /*uint*/long[] pchEaten,       // Pointer to a ULONG value that receives the number of characters of the display name that was parsed.
            /*out*/ IntPtr[] ppidl,        // Pointer to an ITEMIDLIST pointer that receives the item identifier list for the object.
            /*ref*/ /*uint*/long[] pdwAttributes); // Optional parameter that can be used to query for file attributes. This can be values from the SFGAO enum

        // Allows a client to determine the contents of a folder by creating an item identifier enumeration object and returning its IEnumIDList interface.
        // Return value: error code, if any
        @PreserveSig ()
        public /*uint*/long enumObjects(
            IntPtr hwnd,                    // If user input is required to perform the enumeration, this window handle should be used by the enumeration object as the parent window to take user input.
            /*SHCONTF*/long grfFlags,               // Flags indicating which items to include in the enumeration. For a list of possible values, see the SHCONTF enum. 
            /*out*/ IEnumIDList[] ppenumIDList);  // Address that receives a pointer to the IEnumIDList interface of the enumeration object created by this method. 

        // Retrieves an IShellFolder object for a subfolder.
        // Return value: error code, if any
        @PreserveSig ()
        public /*uint*/long bindToObject(
            IntPtr pidl,            // Address of an ITEMIDLIST structure (PIDL) that identifies the subfolder.
            IntPtr pbc,             // Optional address of an IBindCtx interface on a bind context object to be used during this operation.
            @In ()
            /*ref*/ Guid[] riid,          // Identifier of the interface to return. 
            /*out*/ IShellFolder[] ppv);        // Address that receives the interface pointer.

        // Requests a pointer to an object's storage interface. 
        // Return value: error code, if any
        @PreserveSig ()
        public /*uint*/long bindToStorage(
            IntPtr pidl,            // Address of an ITEMIDLIST structure that identifies the subfolder relative to its parent folder. 
            IntPtr pbc,             // Optional address of an IBindCtx interface on a bind context object to be used during this operation.
            @In ()
            /*ref*/ Guid[] riid,          // Interface identifier (IID) of the requested storage interface.
            @MarshalAs (UnmanagedType.Interface)
            /*out*/ Object[] ppv);        // Address that receives the interface pointer specified by riid.

        // Determines the relative order of two file objects or folders, given their item identifier lists. 
        // Return value: If this method is successful, the CODE field of the HRESULT contains one of the following values (the code can be retrived using the helper function GetHResultCode)...
        // A negative return value indicates that the first item should precede the second (pidl1 < pidl2). 
        // A positive return value indicates that the first item should follow the second (pidl1 > pidl2).  Zero A return value of zero indicates that the two items are the same (pidl1 = pidl2). 
        @PreserveSig ()
        public int compareIDs(
            int lParam,             // Value that specifies how the comparison should be performed. The lower sixteen bits of lParam define the sorting rule.
            // The upper sixteen bits of lParam are used for flags that modify the sorting rule. values can be from the SHCIDS enum
            IntPtr pidl1,           // Pointer to the first item's ITEMIDLIST structure.
            IntPtr pidl2);          // Pointer to the second item's ITEMIDLIST structure.

        // Requests an object that can be used to obtain information from or interact with a folder object.
        // Return value: error code, if any
        @PreserveSig ()
        public /*uint*/long createViewObject(
            IntPtr hwndOwner,       // Handle to the owner window.
            @In ()
            /*ref*/ Guid[] riid,          // Identifier of the requested interface.
            @MarshalAs (UnmanagedType.Interface)
            /*out*/ Object[] ppv);        // Address of a pointer to the requested interface. 

        // Retrieves the attributes of one or more file objects or subfolders. 
        // Return value: error code, if any
        @PreserveSig ()
        public /*uint*/long getAttributesOf(
            int cidl,               // Number of file objects from which to retrieve attributes. 
            /*ref*/ IntPtr[] apidl,           // Address of an array of pointers to ITEMIDLIST structures, each of which uniquely identifies a file object relative to the parent folder.
            /*ref*/ /*SFGAOF*/long[] rgfInOut);       // Address of a single ULONG value that, on entry, contains the attributes that the caller is requesting. On exit, this value contains the requested attributes that are common to all of the specified objects. this value can be from the SFGAO enum

        // Retrieves an OLE interface that can be used to carry out actions on the specified file objects or folders. 
        // Return value: error code, if any
        @PreserveSig ()
        public /*uint*/long getUIObjectOf(
            IntPtr hwndOwner,       // Handle to the owner window that the client should specify if it displays a dialog box or message box.
            int cidl,               // Number of file objects or subfolders specified in the apidl parameter. 
            @In () @MarshalAs (UnmanagedType.LPArray) IntPtr[] apidl,                  // Address of an array of pointers to ITEMIDLIST structures, each of which uniquely identifies a file object or subfolder relative to the parent folder.
            @In ()
            /*ref*/ Guid[] riid,          // Identifier of the COM interface object to return.
            IntPtr rgfReserved,     // Reserved. 
            @MarshalAs (UnmanagedType.Interface)
            /*out*/ Object[] ppv);        // Pointer to the requested interface.

        // Retrieves the display name for the specified file object or subfolder. 
        // Return value: error code, if any
        @PreserveSig ()
        public /*uint*/long getDisplayNameOf(
            IntPtr pidl,            // Address of an ITEMIDLIST structure (PIDL) that uniquely identifies the file object or subfolder relative to the parent folder. 
            /*SHGNO*/long uFlags,           // Flags used to request the type of display name to return. For a list of possible values. 
            /*out*/ /*STRRET*/long[] pName);      // Address of a STRRET structure in which to return the display name.

        // Sets the display name of a file object or subfolder, changing the item identifier in the process.
        // Return value: error code, if any
        @PreserveSig ()
        public /*uint*/long setNameOf(
            IntPtr hwnd,            // Handle to the owner window of any dialog or message boxes that the client displays.
            IntPtr pidl,            // Pointer to an ITEMIDLIST structure that uniquely identifies the file object or subfolder relative to the parent folder. 
            @In () @MarshalAs (UnmanagedType.LPWStr) 
            String pszName,         // Pointer to a null-terminated string that specifies the new display name. 
            /*SHGNO*/long uFlags,           // Flags indicating the type of name specified by the lpszName parameter. For a list of possible values, see the description of the SHGNO enum. 
            /*out*/ IntPtr[] ppidlOut);   // Address of a pointer to an ITEMIDLIST structure which receives the new ITEMIDLIST. 
    }

    @ComImport
    @InterfaceType (ComInterfaceType.InterfaceIsIUnknown)
    @Guid ("000214F2-0000-0000-C000-000000000046")
    public interface IEnumIDList
    {

        // Retrieves the specified number of item identifiers in the enumeration sequence and advances the current position by the number of items retrieved. 
        @PreserveSig ()
        public /*uint*/long next(
            /*uint*/long celt,                // Number of elements in the array pointed to by the rgelt parameter.
            /*out*/ IntPtr[] rgelt,         // Address of an array of ITEMIDLIST pointers that receives the item identifiers. The implementation must allocate these item identifiers using the Shell's allocator (retrieved by the SHGetMalloc function). 
            // The calling application is responsible for freeing the item identifiers using the Shell's allocator.
            /*out*/ int[] pceltFetched    // Address of a value that receives a count of the item identifiers actually returned in rgelt. The count can be smaller than the value specified in the celt parameter. This parameter can be NULL only if celt is one. 
            );

        // Skips over the specified number of elements in the enumeration sequence. 
        @PreserveSig ()
        public /*uint*/long skip(
            /*uint*/long celt                 // Number of item identifiers to skip.
            );

        // Returns to the beginning of the enumeration sequence. 
        @PreserveSig ()
        public /*uint*/long reset();

        // Creates a new item enumeration object with the same contents and state as the current one. 
        @PreserveSig ()
        public /*uint*/long deepClone(
            /*out*/ IEnumIDList[] ppenum    // Address of a pointer to the new enumeration object. The calling application must eventually free the new object by calling its Release member function. 
            );
    }

    //<<<<<<<< #endregion 
}

