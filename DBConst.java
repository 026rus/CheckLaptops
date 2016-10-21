
public class DBConst
{
	private DBConst(){}
	
	public static final String DATABASE_NAME 			= "kiosk";
	
	public static final String TABLE_LAPTOP 			= "laptops";
	public static final String TABLE_EMPLOYEE 			= "employee"; 
	public static final String TABLE_RECOREDS 			= "laptoprecords";
	public static final String TABLE_UPDATES			= "laptopupdates";
	
	public static final String LAPTOP_ID 				= "laptops.id";
	public static final String LAPTOP_TAG 				= "laptops.tag";
	public static final String LAPTOP_EMPLOYEE 			= "laptops.empoyee_id";
	public static final String LAPTOP_NOTES 			= "laptops.notes";

	public static final String EMPLOYEE_ID 				= "employee.id";
	public static final String EMPLOYEE_F_NAME 			= "employee.first_name";
	public static final String EMPLOYEE_L_NAME 			= "employee.last_name";
	public static final String EMPLOYEE_M_NAME 			= "employee.middle_name";
	public static final String EMPLOYEE_EMAIL 			= "employee.email";
	public static final String EMPLOYEE_PHOTO 			= "employee.photo";

	public static final String RECORDS_ID 				= "records.id";
	public static final String RECORDS_TAG 				= "records.tag";
	public static final String RECORDS_EMPLOYEE 		= "records.employee";
	public static final String RECORDS_DATEIN 			= "records.datein";
	public static final String RECORDS_DATEOUT 			= "records.dateout"; 

	public static final String DB_USER_NAME 			= "kioskuser";
	public static final String DB_USER_PASS 			= "kioskuser";

	public static final String UPDATE_LAPTOP_UPDATES	= "laptopupdates";
	public static final String UPDATE_LAPTOP_RECORDS	= "laptoprecords";
	public static final String UPDATE_LAPTOP_S			= "laptops";
	
	public static final String LAPTOPRECORDS_ID 		= "laptoprecords.id";
	public static final String LAPTOPRECORDS_TAG		= "laptoprecords.tag";
	public static final String LAPTOPRECORDS_EMPLOYEE	= "laptoprecords.employee";
	public static final String LAPTOPRECORDS_DATEIN		= "laptoprecords.datein";
	public static final String LAPTOPRECORDS_DATEOUT	= "laptoprecords.dateout";

	public static final String PEOPLE_FOLDER			= "people";
	public static final String PORT						= "3306";
	
	public static final int F_NAME 	= 0;
	public static final int L_NAME 	= 1;
	public static final int TAG		= 2;
	public static final int COMENT 	= 3;
	public static final int EMP_ID 	= 4;
	public static final int PHOTO 	= 5;
	
}
