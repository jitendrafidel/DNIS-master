package com.comlink.dao.impl;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.comlink.dao.DnisDao;
import com.comlink.model.DNIS;
import com.comlink.model.DNISTarget;
import com.comlink.rowmapper.DnisListMapper;
import com.comlink.rowmapper.DnisTargetMapper;

public class DnisDaoImpl implements DnisDao {
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	 
	
	
	
	
	
	
	
	
	
	/*public List<DNIS> getDnisByparameter(String dni, String premap, String pre,
			String mnum, String cna, String tic)throws DataAccessException  {
		if(dni.equals(""))
		{
			dni=null;
		}
		if(premap.equals(""))
		{
			premap=null;
		}
		if(pre.equals(""))
		{
			pre=null;
		}
		if(mnum.equals(""))
		{
			mnum=null;
		}
		if(cna.equals(""))
		{
			cna=null;
		}
		if(tic.equals(""))
		{
			tic=null;
		}
		
	  List<DNIS> list = jdbcTemplate.query( "call getReportData(?,?,?,?,?,?)",new DnisListMapper(),dni,premap, pre, mnum, cna,tic);
        return list;
	}*/

	 
	/*public List<DNIS> getDownloadReportList(String dni, String premap,
			String pre, String mnum, String cna, String tic)throws DataAccessException {
		
		
		if(dni.equals(""))
		{
			dni=null;
		}
		if(premap.equals(""))
		{
			premap=null;
		}
		if(pre.equals(""))
		{
			pre=null;
		}
		if(mnum.equals(""))
		{
			mnum=null;
		}
		if(cna.equals(""))
		{
			cna=null;
		}
		if(tic.equals(""))
		{
			tic=null;
		}
		
		List<DNIS> list =jdbcTemplate.query("call getReportData(?,?,?,?,?,?)",new DnisListMapper(),dni,premap, pre, mnum, cna,tic);
		return list;
	} */

	@Override
	public int uploadFile(DNIS dnis) {
	    return jdbcTemplate.update("call uploadFile(?)",dnis.getFilename());
     }
    @Override
	public int uploadFile(String s) {
		 return jdbcTemplate.update("call upload_file(?)",s);
	 }
    @Override
	public int uploadData(String s) {
		 return jdbcTemplate.update("call uploadData(?)",s);
	}
    @Override
	public int uploadFileData(String dni, String pre, String mnum, String cna,
			String rip1, String rip2, String rip3, String rip4, String rip5,
			String rip6, String datec, String datem, String tic, String dtfs,
			String status, String fname,int coutRow) {
		return jdbcTemplate.update("call upload_file_data(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",dni,pre,mnum,cna,rip1,rip2,rip3,rip4,rip5,rip6,datec,datem,tic,dtfs,status,fname,java.sql.Types.INTEGER);
	}
    @Override
	public List<DNISTarget> getAllDnis() {
		 List<DNISTarget> list = jdbcTemplate.query("call get_all_dnis()", new DnisTargetMapper());
		 System.out.println(list.size()+"size......");
	  return list;
	 
    }
    @Override
	public Map<DNIS, Object> getDnis(DNIS dni) {
		 Map<DNIS, Object> map = (Map<DNIS, Object>) jdbcTemplate.query("call get_dnis()",
			new DnisListMapper());
	  return map;
	}











	@Override
	public List<DNIS> getDnisByparameter(String status, String prefix_dnis,
			String file, String did, String ticnumber, String gatewayname,String datem) {
		
		System.out.println("Values are as:"+status+""+prefix_dnis+""+datem+""+file+""+did+""+ticnumber+""+gatewayname);
		 
		String condition = "";
		if ((status != null) && (!status.isEmpty())) {
			condition = condition + "Status like '%" + status + "%' OR ";
		}
		if ((prefix_dnis != null) && (!prefix_dnis.isEmpty())) {
			condition = condition + "concat(Prefix,DNIS) like '%"
					+ prefix_dnis + "%' OR ";
		}
		if ((datem != null) && (!datem.isEmpty())) {
			condition = condition + "DateModified like '" + datem + "%' OR ";
		}
		if ((file != null) && (!file.isEmpty())) {
			condition = condition + "Filename like '%" + file + "%' OR ";
		}
		if ((did != null) && (!did.isEmpty())) {
			condition = condition + "DIDnumber like '%" + did + "%' OR ";
		}
		if ((ticnumber != null) && (!ticnumber.isEmpty())) {
			condition = condition + "TicketOrderNum like '%" + ticnumber + "%' OR ";
		}
		if ((gatewayname != null) && (!gatewayname.isEmpty())) {
			condition = condition + "GatewayGroupName like '%" + gatewayname + "%' OR ";
		}
		String sql = null;
		if (condition.isEmpty()) {
			sql = "select * from dnism";
		} else {
			condition = condition.substring(0, condition.length()-3);
			sql = "select * from dnism where " + condition;
		}

		List<DNIS> list = jdbcTemplate.query(sql, new DnisListMapper());
		return list;
	}




	@Override
	public List<DNIS> getDownloadReportList(String status, String prefix_dnis,
			String file, String did, String ticnumber, String gatewayname,
			String datem) {
		String condition = "";
		if ((status != null) && (!status.isEmpty())) {
			condition = condition + "Status like '%" + status + "%' OR ";
		}
		if ((prefix_dnis != null) && (!prefix_dnis.isEmpty())) {
			condition = condition + "concat(Prefix,DNIS) like '%"
					+ prefix_dnis + "%' OR ";
		}
		if ((datem != null) && (!datem.isEmpty())) {
			condition = condition + "DateModified like '" + datem + "%' OR ";
		}
		if ((file != null) && (!file.isEmpty())) {
			condition = condition + "Filename like '%" + file + "%' OR ";
		}
		if ((did != null) && (!did.isEmpty())) {
			condition = condition + "DIDnumber like '%" + did + "%' OR ";
		}
		if ((ticnumber != null) && (!ticnumber.isEmpty())) {
			condition = condition + "TicketOrderNum like '%" + ticnumber + "%' OR ";
		}
		if ((gatewayname != null) && (!gatewayname.isEmpty())) {
			condition = condition + "GatewayGroupName like '%" + gatewayname + "%' OR ";
		}
		String sql = null;
		if (condition.isEmpty()) {
			sql = "select * from dnism";
		} else {
			condition = condition.substring(0, condition.length()-3);
			sql = "select * from dnism where " + condition;
		}

		List<DNIS> list = jdbcTemplate.query(sql, new DnisListMapper());
		return list;
	}



	@Override
	public int addAction(String ticnum, String action) {
		 
		String sql="update   dnism set CurrentAction='"+action+"' where TicketOrderNum='"+ticnum+"'";
		
		return   jdbcTemplate.update(sql);  
		
	}




	 
		
		
		 
}
