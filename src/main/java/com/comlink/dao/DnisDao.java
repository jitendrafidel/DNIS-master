package com.comlink.dao;

import java.util.List;
import java.util.Map;

import com.comlink.model.DNIS;
import com.comlink.model.DNISTarget;
 

public interface DnisDao 
{ 
	public List<DNIS> getDnisByparameter(String status,
			String prefix_dnis, String file, String did, String ticnumber,String gatewayname,String datem);
	public List<DNIS> getDownloadReportList(String status,
			String prefix_dnis, String file, String did, String ticnumber,String gatewayname,String datem);
	public int uploadFile(String s);
	public int uploadFileData(String dni, String pre, String mnum, String cna,
			String rip1, String rip2, String rip3, String rip4, String rip5,
			String rip6, String datec, String datem, String tic, String dtfs,
			String status, String fname,int countRow);
	
	public int uploadFile(DNIS dnis);
	public int uploadData(String s);

	public List<DNISTarget> getAllDnis();
	public Map<DNIS,Object> getDnis(DNIS dni);
	
	public int addAction(String ticnum,String action);

}


 