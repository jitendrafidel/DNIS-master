package com.comlink.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.comlink.dao.ConfigDao;
import com.comlink.dao.DnisDao;
import com.comlink.dao.LogDao;
import com.comlink.dao.UserDao;
import com.comlink.model.Config;
import com.comlink.model.DNIS;
import com.comlink.model.DNISTarget;
import com.comlink.model.Log;
import com.comlink.model.User;

@Controller
@RequestMapping("/")
public class MasterController extends MultiActionController {

	@Autowired
	private UserDao userDAO;

	@Autowired
	private LogDao logDAO;

	@Autowired
	private DnisDao dnisDAO;

	@Autowired
	private ConfigDao configDao;
	

	public UserDao getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDao userDAO) {
		this.userDAO = userDAO;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap model) {
		return "index";
	}
      @Value("${upload.file.path}")
      private String  path;

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ModelAndView uploadFile(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		response.setContentType("application/vnd.ms-excel");
		ModelAndView modelview = new ModelAndView();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("file");
		if ((multipartFile != null)
				&& (multipartFile.getOriginalFilename() != null)
				&& (!multipartFile.getOriginalFilename().isEmpty())) {
			DNIS dnis = new DNIS();
			String s = multipartFile.getOriginalFilename();
			dnis.setFilename(multipartFile.getOriginalFilename());
			ServletContext context = request.getServletContext();
			File fn1 = new File(context.getRealPath("/") + "/" + s);
			String dirPat = fn1.getAbsolutePath().toString();
			
			

			int Data=0;
			int Count=0;
			
			String filePath = dirPat.substring(0,
					dirPat.lastIndexOf(File.separator));
			try {
				String rid = null;
				String Dnis = null;
				String prefix = null;
				String mnumber = null;
				String cname = null;
				String rip1 = null;
				String rip2 = null;
				String rip3 = null;
				String rip4 = null;
				String rip5 = null;
				String rip6 = null;
				String datec = null;
				String datem = null;
				String tic = null;
				String dtfs = null;
				String status = null;
				String fname = null;

				List<Object> list = new ArrayList<Object>();
				ArrayList<Object> cellArrayLisstHolder = new ArrayList<Object>();
				String filename = dirPat;
				File file = new File(filename);
				FileInputStream fn = new FileInputStream(file);
				InputStream input = new BufferedInputStream(fn);
				POIFSFileSystem fs = new POIFSFileSystem(input);
				HSSFWorkbook wb = new HSSFWorkbook(fs);
				HSSFSheet sheet = wb.getSheetAt(0);

				
				
				Iterator rows = sheet.rowIterator();
				while (rows.hasNext()) {
					HSSFRow row = (HSSFRow) rows.next();
				
					Iterator cells = row.cellIterator();
					ArrayList cellStoreArrayList = new ArrayList();
					while (cells.hasNext()) {
						HSSFCell myCell = (HSSFCell) cells.next();
						cellStoreArrayList.add(myCell);
					}
					cellArrayLisstHolder.add(cellStoreArrayList);
					for (int i = 1; i < cellArrayLisstHolder.size(); i++) {
						cellStoreArrayList = (ArrayList) cellArrayLisstHolder
								.get(i);
						rid = cellStoreArrayList.get(0).toString();
						Dnis = cellStoreArrayList.get(1).toString();
						prefix = cellStoreArrayList.get(2).toString();
						mnumber = cellStoreArrayList.get(3).toString();
						cname = cellStoreArrayList.get(4).toString();
						rip1 = cellStoreArrayList.get(5).toString();
						rip2 = cellStoreArrayList.get(6).toString();
						rip3 = cellStoreArrayList.get(7).toString();
						rip4 = cellStoreArrayList.get(8).toString();
						rip5 = cellStoreArrayList.get(9).toString();
						rip6 = cellStoreArrayList.get(10).toString();
						datec = cellStoreArrayList.get(11).toString();
						datem = cellStoreArrayList.get(12).toString();
						tic = cellStoreArrayList.get(13).toString();
						dtfs = cellStoreArrayList.get(14).toString();
						status = cellStoreArrayList.get(15).toString();
						fname = cellStoreArrayList.get(16).toString();
						
						DNIS dn = new DNIS();
						dn.setDNIS(Dnis);
						dn.setPrefix(prefix);
						dn.setMnumber(mnumber);
						dn.setCname(cname);
						dn.setRip1(rip1);
						dn.setRip2(rip2);
						dn.setRip3(rip3);
						dn.setRip4(rip4);
						dn.setRip5(rip5);
						dn.setRip6(rip6);
						dn.setDatec(datec);
						dn.setDatem(datem);
						dn.setTicketno(tic);
						dn.setDtfs(dtfs);
						dn.setStatus(status);
						dn.setFilename(fname);
						
						
						
				  Data=	dnisDAO.uploadFileData(Dnis, prefix, mnumber, cname, rip1,
								rip2, rip3, rip4, rip5, rip6, datec, datem, tic, dtfs,
								status, fname,Data);
				  System.out.println("Affected rows are as:"+Data);
			
				   
			}
					
				}
				
			 
				
				
				modelview.setViewName("dnismanagement");
				modelview.addObject("sessionuser", user);
				modelview.addObject("message", "Record Added successfully.");
				modelview.setViewName("dnismanagement");
				logDAO.LogEvent(user.getFirstName(), user.getLastName(),
						user.getId(), "Uploaded File " + filePath);
			} catch (Exception e) 
			{
				e.printStackTrace();
				modelview.addObject("sessionuser", user);
				modelview.setViewName("dnismanagement");
				modelview.addObject("message", "Please Try Again.");
			}

		} else {
			modelview.addObject("sessionuser", user);
			modelview.addObject("message", "Please select file.");
			modelview.setViewName("dnismanagement");
		}
		return modelview;
	}

	@RequestMapping(value = "/downloadReport", method = RequestMethod.POST)
	public ModelAndView downloadReport(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		ModelAndView modelview = new ModelAndView();
		User user = (User) session.getAttribute("user");
		modelview.addObject("sessionuser", user);
		modelview.setViewName("dnismanagement");
		ServletOutputStream stream = null;
		
		
		String status = request.getParameter("Status");
		String datem = request.getParameter("datem");
		String prefix_dnis = request.getParameter("prefixdnis");
		String file = request.getParameter("file");
		String did = request.getParameter("did");
		String ticnumber = request.getParameter("ticnumber");
		String gatewayname = request.getParameter("gatewayname");
		
	System.out.println();
	
	System.out.println("Values are as con:"+status+""+prefix_dnis+""+datem+""+file+""+did+""+ticnumber+""+gatewayname);
	 
		
		if(!status.isEmpty()||!datem.isEmpty() || !prefix_dnis.isEmpty() || !file.isEmpty()
				|| !did.isEmpty() || !ticnumber.isEmpty() || !gatewayname.isEmpty())
		{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition",
					"attachment; filename=Report.xls");
			
			
			
		
		List<DNIS> downloadReportLst = dnisDAO.getDownloadReportList(status,
				prefix_dnis, file, did, ticnumber,gatewayname,datem);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Report");
		int rowIndex = 0;
		Row rowheader = sheet.createRow(rowIndex++);
		rowheader.createCell(0).setCellValue("RecordID");
		rowheader.createCell(1).setCellValue("DNIS");
		rowheader.createCell(2).setCellValue("Prefix");
		rowheader.createCell(3).setCellValue("MappedNumber");
		rowheader.createCell(4).setCellValue("CustomerName");
		rowheader.createCell(5).setCellValue("RoutingIP1");
		rowheader.createCell(6).setCellValue("RoutingIP2");
		rowheader.createCell(7).setCellValue("RoutingIP3");
		rowheader.createCell(8).setCellValue("RoutingIP4");
		rowheader.createCell(9).setCellValue("RoutingIP5");
		rowheader.createCell(10).setCellValue("RoutingIP6");
		rowheader.createCell(11).setCellValue("DateCreated");
		rowheader.createCell(12).setCellValue("DateModified");
		rowheader.createCell(13).setCellValue("TicketOrderNum");
		rowheader.createCell(14).setCellValue("DTFS");
		rowheader.createCell(15).setCellValue("Status");
		rowheader.createCell(16).setCellValue("File Name");
		rowheader.createCell(17).setCellValue("userID");
		rowheader.createCell(18).setCellValue("DIDnumber");
		rowheader.createCell(19).setCellValue("GatewayGroupName");
		rowheader.createCell(20).setCellValue("InitialActionBy");
		rowheader.createCell(21).setCellValue("CurrentAction");
		for (DNIS report : downloadReportLst) {
			Row row = sheet.createRow(rowIndex++);
			row.createCell(0).setCellValue((  report).getRecordid());
			row.createCell(1).setCellValue(( report).getDNIS());
			row.createCell(2).setCellValue(( report).getPrefix());
			row.createCell(3).setCellValue((  report).getMnumber());
			row.createCell(4).setCellValue((  report).getCname());
			row.createCell(5).setCellValue((  report).getRip1());
			row.createCell(6).setCellValue((  report).getRip2());
			row.createCell(7).setCellValue((  report).getRip3());
			row.createCell(8).setCellValue((  report).getRip4());
			row.createCell(9).setCellValue((report).getRip5());
			row.createCell(10).setCellValue((  report).getRip6());
			row.createCell(11).setCellValue((  report).getDatec());
			row.createCell(12).setCellValue(( report).getDatem());
			row.createCell(13).setCellValue((  report).getTicketno());
			row.createCell(14).setCellValue((  report).getDtfs());
			row.createCell(15).setCellValue((  report).getStatus());
			row.createCell(16).setCellValue((  report).getFile());
			row.createCell(17).setCellValue((  report).getUserId());
			row.createCell(18).setCellValue((  report).getDidNumber());
			row.createCell(19).setCellValue((  report).getGatewayName());
			row.createCell(20).setCellValue((  report).getInitialAction());
			row.createCell(21).setCellValue((  report).getCurrentAction());
		}
		
		try {
			workbook.write(response.getOutputStream());
			stream=response.getOutputStream();
			workbook.close();
			stream.flush();
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logDAO.LogEvent(user.getFirstName(), user.getLastName(), user.getId(),
				"Downloaded Report");
	}
		
	
	else
	{
		modelview.addObject("sessionuser", user);
		modelview.addObject("message", "Please Search Data First.");
		modelview.setViewName("dnismanagement");
		 
		 
		return modelview;
		
	}
		 
		return modelview;
	}
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelview = new ModelAndView();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			modelview.setViewName("dashboard");
			modelview.addObject("sessionuser", user);
			return modelview;
		} else {
			modelview.setViewName("index");
			modelview.addObject("message", " Insert data first.");
		}
		return modelview;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView user(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelview = new ModelAndView();
		
		HttpSession session = request.getSession(false);
		User sessionuser = (User) session.getAttribute("user");
		List<User> users = userDAO.getAllUser();
		modelview.addObject("userList", users);
		modelview.addObject("sessionuser", sessionuser);
		modelview.setViewName("user");
		return modelview;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ModelAndView adduser(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		User sessionuser = (User) session.getAttribute("user");
		
		ModelAndView modelview = new ModelAndView();
		String action = null;
		if (request.getParameter("action") != null) {
			action = request.getParameter("action");
			if (action.equals("modify")) {
				String id = request.getParameter("userID");
			
				String firstName = request.getParameter("firstname");
				String lastName = request.getParameter("lastname");
				String userName = request.getParameter("username");
				String type = request.getParameter("type");
			 
				User user = new User();
				if (id != null) {
					user = userDAO.getUserById(id);
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setType(type);
					user.setUserName(userName);
					int use=userDAO.editUser(user);
					
					
					if(use==1)
					{
						modelview.addObject("message", "Record Updated Successfully.");
						modelview.setViewName("user");
					}
					else
					{
						modelview.addObject("message", "Record Not Updated.");
						modelview.setViewName("user");
					}
					 
				}
				logDAO.LogEvent(sessionuser.getFirstName(),
						sessionuser.getLastName(), sessionuser.getId(),
						"Update User " + user.getFirstName());
			}
			if (action.equals("add")) 
			{
				
				
				User user = new User();
				user.setFirstName(request.getParameter("firstname"));
				user.setLastName(request.getParameter("lastname"));
				user.setUserName(request.getParameter("username"));
				user.setPassword(request.getParameter("password"));
				user.setType(request.getParameter("type"));
				
				int user1=userDAO.registerUser(user);
				if(user1==-1)
				{
					modelview.addObject("message", "Username Already Exist.");
					modelview.setViewName("user");
				}
				else
				{
					modelview.addObject("message", "User Created Successfully.");
					modelview.setViewName("user");
				}
				logDAO.LogEvent(sessionuser.getFirstName(),
						sessionuser.getLastName(), sessionuser.getId(),
						"Added User " + user.getFirstName());
			}
		}
		List<User> users = userDAO.getAllUser();
		modelview.addObject("userList", users);
		modelview.addObject("sessionuser", sessionuser);
		modelview.setViewName("user");
		return modelview;
	}

	@RequestMapping(value = "/deleteUser")
	public ModelAndView deleteuser(@RequestParam("id") String id,HttpServletRequest request, HttpServletResponse response,
		ModelMap model) {
		HttpSession session = request.getSession(false);
		User sessionuser = (User) session.getAttribute("user");
		ModelAndView modelview = new ModelAndView();
		int use=userDAO.deleteUser(id);
		
		if(use==1)
		{
			modelview.addObject("message", "User Deleted...");
			modelview.setViewName("user");
		}
		else
		{
			modelview.addObject("message", "User Not Deleted.");
			modelview.setViewName("user");
		}
		
		
		List<User> users = userDAO.getAllUser();
		modelview.addObject("userList", users);
		modelview.setViewName("user");
		modelview.addObject("sessionuser", sessionuser);
		logDAO.LogEvent(sessionuser.getFirstName(), sessionuser.getLastName(),
				sessionuser.getId(), "Deleted User ");
		return modelview;
	}

	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public ModelAndView logs(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		User sessionuser = (User) session.getAttribute("user");
		ModelAndView modelview = new ModelAndView();
		List<Log> logs = logDAO.getAllLogs();
		modelview.addObject("logList", logs);
		modelview.setViewName("log");
		modelview.addObject("sessionuser", sessionuser);
		return modelview;
	}
	
	
	
	@RequestMapping(value = "/addaction", method = RequestMethod.POST)
	public ModelAndView addAction(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		User sessionuser = (User) session.getAttribute("user");
		ModelAndView modelview = new ModelAndView();
		
		String tic_number=request.getParameter("tic_number");		
		String add_action=request.getParameter("add_action");		
		
		System.out.println("add values"+tic_number);
		System.out.println("add values"+add_action);
		
		int i=dnisDAO.addAction(tic_number,add_action);
		
		
		System.out.println("upadted data value"+i);
		
		modelview.addObject("sessionuser", sessionuser);
		modelview.setViewName("dnismanagement");
		return modelview;
	}

	

	@RequestMapping(value = "/dnismanagement", method = RequestMethod.POST)
	public ModelAndView dnis(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		User sessionuser = (User) session.getAttribute("user");
		ModelAndView modelview = new ModelAndView();
		
		
		String status = request.getParameter("Status");
		String datem = request.getParameter("datem");
		String prefix_dnis = request.getParameter("prefixdnis");
		String file = request.getParameter("file");
		String did = request.getParameter("did");
		String ticnumber = request.getParameter("ticnumber");
		String gatewayname = request.getParameter("gatewayname");
		
	System.out.println("inside dnismanagement");
	
	System.out.println("Values are as con:"+status+""+prefix_dnis+""+datem+""+file+""+did+""+ticnumber+""+gatewayname);
	 
		
		if(!status.isEmpty()||!datem.isEmpty() || !prefix_dnis.isEmpty() || !file.isEmpty()
				|| !did.isEmpty() || !ticnumber.isEmpty() || !gatewayname.isEmpty())
		{
		List<DNIS> dnisList = dnisDAO.getDnisByparameter(status,
				prefix_dnis, file, did, ticnumber,gatewayname,datem);
		
		 

		modelview.addObject("status", status);
		modelview.addObject("datem", datem);
		modelview.addObject("prefixdnis", prefix_dnis);
		modelview.addObject("file", file);
		modelview.addObject("did", did);
		modelview.addObject("ticnumber", ticnumber);
		modelview.addObject("gatewayname",gatewayname);
		
		modelview.addObject("dnisList", dnisList);
		modelview.addObject("sessionuser", sessionuser);
		modelview.setViewName("dnismanagement");
		}
		else
		{   
			modelview.addObject("message", "Insert data first.");
			modelview.addObject("sessionuser", sessionuser);
			modelview.setViewName("dnismanagement");
			
			
		}
		return modelview;
	}

	@RequestMapping(value = "/dnismanagement", method = RequestMethod.GET)
	public ModelAndView dnisShow(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		User sessionuser = (User) session.getAttribute("user");
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("sessionuser", sessionuser);
		modelview.setViewName("dnismanagement");
		return modelview;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(password);
		ModelAndView modelview = new ModelAndView();
		User user = userDAO.login(username, password);
		if (user != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			modelview.setViewName("dashboard");
			modelview.addObject("sessionuser", user);
			logDAO.LogEvent(user.getFirstName(), user.getLastName(),
					user.getId(), "Login User " + user.getFirstName());
			modelview.addObject("message", "Login Successfully.");
		} else {
			modelview.setViewName("index");
			modelview.addObject("message", "Username or Password are wrong!");
		}
		return modelview;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		logDAO.LogEvent(user.getFirstName(), user.getLastName(), user.getId(),
				"Logout User " + user.getFirstName());
		session.invalidate();
		return "index";
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public String changepasswordget(ModelMap model, HttpServletRequest request) {
		return "changepassword";
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public ModelAndView changepasswordpost(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelview = new ModelAndView();
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		if (userDAO.updateUser(user.getId(), request.getParameter("password")) == 1) {
			modelview.setViewName("dashboard");
			modelview.addObject("message", "Password Changed Successfully.");
			modelview.addObject("sessionuser", user);
		} else {
			modelview.setViewName("dashboard");
			modelview.addObject("sessionuser", user);
			modelview.addObject("message", "Please Try Again.");
		}
		return modelview;
	}

	@RequestMapping(value = "/uploadconfigFile", method = RequestMethod.POST)
	public ModelAndView uploadconfigpost(ModelMap model,
		HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		ModelAndView modelview = new ModelAndView();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("file1");
		Config config = new Config();
		String filename = multipartFile.getOriginalFilename();
		config.setFile(multipartFile.getOriginalFilename());
		ServletContext context = request.getServletContext();
		File file = new File(context.getRealPath("/") + "/" + filename);
		String dirPat = file.getAbsolutePath().toString();
		String filePath = dirPat.substring(0,
				dirPat.lastIndexOf(File.separator));
		try {
			configDao.uploadconfigFile(filename);
			modelview.setViewName("dnismanagement");
		} catch (Exception e) {
			e.printStackTrace();
			modelview.addObject("sessionuser", user);
			modelview.setViewName("dnismanagement");
			modelview.addObject("message",
					"Duplicate record, Please Try Again.");
		}
		modelview.addObject("sessionuser", user);
		modelview.addObject("message", "Record Added successfully.");
		modelview.setViewName("dnismanagement");
		logDAO.LogEvent(user.getFirstName(), user.getLastName(), user.getId(),
				"Uploaded File " + filePath);
		return modelview;
	}

	@RequestMapping(value = "/downloadConfig", method = RequestMethod.POST)
	public ModelAndView downloadConfig(ModelMap model, HttpServletRequest request,
		HttpServletResponse response) throws IOException,
		TransformerException {
        HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		ModelAndView modelview = new ModelAndView();
		
		try {
			 
			File fXmlFile = new File(path);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			Node dnisMap = doc.getElementsByTagName("dnis-map").item(0);
			NodeList maps = dnisMap.getChildNodes();

			for (int temp = 0; temp < maps.getLength(); temp++) {
				Node nNode = maps.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					dnisMap.removeChild(nNode);
				}
			}

			List<DNISTarget> dnisLst = dnisDAO.getAllDnis();
			for (DNISTarget dnisTarget : dnisLst) {
				Element newmap = doc.createElement("map");
				newmap.setAttribute("dnis", dnisTarget.getDNIS());
				newmap.setAttribute("target", dnisTarget.getTarget());
				dnisMap.appendChild(newmap);

			}
            DOMSource source = new DOMSource(doc);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			StreamResult result = new StreamResult(new File(path));
			transformer.transform(source, result);
			
			
			modelview.addObject("message","File Updated Succesfully.");
			modelview.setViewName("dnismanagement");


		} catch (ParserConfigurationException pce) {
			System.out.println(pce.getMessage());
		} catch (SAXException se) {
			System.out.println(se.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
			ioe.printStackTrace();
		}
		
		modelview.addObject("sessionuser", user);
		modelview.addObject("message","File Updated Succesfully.");
		modelview.setViewName("dnismanagement");
		return  modelview;
	}
}