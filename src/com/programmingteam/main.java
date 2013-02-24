package com.programmingteam;

import java.io.File;
import java.util.List;

import com.programmingteam.qsync.QSync;
import com.programmingteam.qsync.QSyncImport;
import com.programmingteam.qsync.QSyncVcxproj;
import com.programmingteam.vs2010.VcxprojSync;

public class Main
{
	public static void main(String[] args)
	{		
		File qsyncFile = new File(args[0]);
		
		if(args.length==1 && qsyncFile.exists())
		{
			//Read file
			QSync syncFiles = new QSync(qsyncFile);
			syncFiles.debugPrint();
			System.out.println("Qsyn created ok.");
			
			List<QSyncVcxproj> projs = syncFiles.getProjects();
			for(QSyncVcxproj p : projs)
			{
				VcxprojSync proj = new VcxprojSync(p.getVcxproj(), p.getVcxprojFilters());
				for(QSyncImport imp: p.getImportList())
				{
					System.out.println("To Filter: " + imp.getToFilter());
					System.out.println("include: " + imp.getInclude());
					System.out.println("src: " + imp.getSrc());
				}
			}
		}
		else
		{
			//Run UI
			System.err.println("App does not have any UI...");
		}
	}
}
