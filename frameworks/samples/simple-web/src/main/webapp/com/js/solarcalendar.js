//Javascript name: Solar Calendar
//Date created: Shiva Shabani
//Scripter: Shiva Shabani
//FileName: SolarCalendar.js
//Version: 1.0
//Contact: Shiva_shn@yahoo.com

//Global variables
var winCal;
var dtToday=ConvertToSolar();
var Cal;
var d=0;
var docCal;
var MonthName=["&#1601;&#1585;&#1608;&#1585;&#1583;&#1740;&#1606;"//farvardin
			   ,"&#1575;&#1585;&#1583;&#1740;&#1576;&#1607;&#1588;&#1578;"//ordibehesht
			   ," &#1582;&#1585;&#1583;&#1575;&#1583;"//khordad
			   ," &#1578;&#1740;&#1585;"//tir
			   ,"&#1605;&#1585;&#1583;&#1575;&#1583;"//mordad
			   ,"&#1588;&#1607;&#1585;&#1740;&#1608;&#1585;"//shahrivar
			   ,"&#1605;&#1607;&#1585;"//mehr
			   ,"&#1570;&#1576;&#1575;&#1606;"//aban
			   ,"&#1570;&#1584;&#1585;"//azar
			   ,"&#1583;&#1740;"//dey
			   ,"&#1576;&#1607;&#1605;&#1606;"//bahman
			   ,"&#1575;&#1587;&#1601;&#1606;&#1583;"//esfand
			   ];

var WeekDayName=["&#1588;&#1606;&#1576;&#1607;"//Saturday
				 ,"&#1740;&#1705;&#1588;&#1606;&#1576;&#1607;"//Sunday
				 ,"&#1583;&#1608;&#1588;&#1606;&#1576;&#1607;"//Monday
				 ,"&#1587;&#1607; &#1588;&#1606;&#1576;&#1607;"//Tuesday
				 ,"&#1670;&#1607;&#1575;&#1585;&#1588;&#1606;&#1576;&#1607;"//Wednesday
				 ,"&#1662;&#1606;&#1580; &#1588;&#1606;&#1576;&#1607;"//Thursday
				 ,"&#1580;&#1605;&#1593;&#1607;"//Friday
				 ];	
var exDateTime;//Existing Date and Time

//Configurable parameters
var cnTop="200";//top coordinate of calendar window.
var cnLeft="500";//left coordinate of calendar window
var WindowTitle ="&#1578;&#1602;&#1608;&#1740;&#1605; &#1588;&#1605;&#1587;&#1740;";//Date Time Picker title.
var CellWidth=20;//Width of day cell.
var DateSeparator="/";//Date Separator, you can change it to "/" if you want.

var ShowLongMonth=true;//Show long month name in Calendar header. example: "January".
var ShowMonthYear=true;//Show Month and Year in Calendar header.
var FontColor="blue";//color of font in Calendar day cell.
//end Configurable parameters
//end Global variable

/*  MOD  --  Modulus function which works for non-integers.  */

function mod(a, b)
{
    return a - (b * Math.floor(a / b));
}
//  LEAP_GREGORIAN  --  Is a given year in the Gregorian calendar a leap year ?

function leap_gregorian(year)
{
    return ((year % 4) == 0) &&
            (!(((year % 100) == 0) && ((year % 400) != 0)));
}

//  GREGORIAN_TO_JD  --  Determine Julian day number from Gregorian calendar date

var GREGORIAN_EPOCH = 1721425.5;

function gregorian_to_jd(year, month, day)
{
    return (GREGORIAN_EPOCH - 1) +
           (365 * (year - 1)) +
           Math.floor((year - 1) / 4) +
           (-Math.floor((year - 1) / 100)) +
           Math.floor((year - 1) / 400) +
           Math.floor((((367 * month) - 362) / 12) +
           ((month <= 2) ? 0 :
                               (leap_gregorian(year) ? -1 : -2)
           ) +
           day);
}

//  JD_TO_GREGORIAN  --  Calculate Gregorian calendar date from Julian day

function jd_to_gregorian(jd) {
    var wjd, depoch, quadricent, dqc, cent, dcent, quad, dquad,
        yindex, dyindex, year, yearday, leapadj;

    wjd = Math.floor(jd - 0.5) + 0.5;
    depoch = wjd - GREGORIAN_EPOCH;
    quadricent = Math.floor(depoch / 146097);
    dqc = mod(depoch, 146097);
    cent = Math.floor(dqc / 36524);
    dcent = mod(dqc, 36524);
    quad = Math.floor(dcent / 1461);
    dquad = mod(dcent, 1461);
    yindex = Math.floor(dquad / 365);
    year = (quadricent * 400) + (cent * 100) + (quad * 4) + yindex;
    if (!((cent == 4) || (yindex == 4))) {
        year++;
    }
    yearday = wjd - gregorian_to_jd(year, 1, 1);
    leapadj = ((wjd < gregorian_to_jd(year, 3, 1)) ? 0
                                                  :
                  (leap_gregorian(year) ? 1 : 2)
              );
    month = Math.floor((((yearday + leapadj) * 12) + 373) / 367);
    day = (wjd - gregorian_to_jd(year, month, 1)) + 1;

    return new Date(year, month, day);
}

//  PERSIAN_TO_JD  --  Determine Julian day from Persian date

var PERSIAN_EPOCH = 1948320.5;

function persian_to_jd(year, month, day)
{
    var epbase, epyear;

    epbase = year - ((year >= 0) ? 474 : 473);
    epyear = 474 + mod(epbase, 2820);

    return day +
            ((month <= 7) ?
                ((month - 1) * 31) :
                (((month - 1) * 30) + 6)
            ) +
            Math.floor(((epyear * 682) - 110) / 2816) +
            (epyear - 1) * 365 +
            Math.floor(epbase / 2820) * 1029983 +
            (PERSIAN_EPOCH - 1);
}

//  calcPersian  --  Update from Persian calendar

function SolarToGrm(yy,mm,dd)
{
	var  date;
    date = jd_to_gregorian(persian_to_jd(yy,mm+1,dd));
		aa=gregorian_to_jd(date.getYear(),date.getMonth(),date.getDate());
	
    return(PERSIAN_WEEKDAYS[jwday(aa)]);

}
function jwday(j)
{
    return mod(Math.floor((j + 1.5)), 7);
}

var PERSIAN_WEEKDAYS = new Array(1,2,3,4,5,6,0);

function ConvertToSolar(){
    a = new Date(); 
    d= a.getDay(); 
    day= a.getDate(); 
    month = a.getMonth()+1; 
    year= a.getYear();

    if (year== 0){year=2000;} 
    if (year<100){year +=1900;} 
    y=1; 
    for(i=0;i<3000;i+=4) { 
        if (year==i) {y=2;} 
        } 
    for(i=1;i<3000;i+=4) { 
        if (year==i) {y=3;} 
        } 
	if (y==1) { 
        year -= ( (month < 3) || ((month == 3) && (day < 21)) )? 622:621; 

        switch (month) { 
            case 1: (day<21)? (month=10, day+=10):(month=11, day-=20); break; 
            case 2: (day<20)? (month=11, day+=11):(month=12, day-=19); break; 
            case 3: (day<21)? (month=12, day+=9):(month=1, day-=20);   break; 
            case 4: (day<21)? (month=1, day+=11):(month=2, day-=20);   break; 
            case 5: 
            case 6: (day<22)? (month-=3, day+=10):(month-=2, day-=21); break; 
            case 7: 
            case 8: 
            case 9: (day<23)? (month-=3, day+=9):(month-=2, day-=22);  break; 
            case 10:(day<23)? (month=7, day+=8):(month=8, day-=22);    break; 
            case 11: 
            case 12:(day<22)? (month-=3, day+=9):(month-=2, day-=21);  break; 
       default:          break; 
        } 
        } 
	if (y==2) { 
        year -= ( (month < 3) || ((month == 3) && (day < 20)) )? 622:621; 

        switch (month) { 
            case 1: (day<21)? (month=10, day+=10):(month=11, day-=20); break; 
            case 2: (day<20)? (month=11, day+=11):(month=12, day-=19); break; 
            case 3: (day<20)? (month=12, day+=10):(month=1, day-=19);   break; 
            case 4: (day<20)? (month=1, day+=12):(month=2, day-=19);   break; 
            case 5: (day<21)? (month=2, day+=11):(month=3, day-=20);   break; 
            case 6: (day<21)? (month=3, day+=11):(month=4, day-=20); break; 
            case 7: (day<22)? (month=4, day+=10):(month=5, day-=21);   break; 
            case 8: (day<22)? (month=5, day+=10):(month=6, day-=21);   break; 
            case 9: (day<22)? (month=6, day+=10):(month=7, day-=21);  break; 
            case 10:(day<22)? (month=7, day+=9):(month=8, day-=21);    break; 
            case 11:(day<21)? (month=8, day+=10):(month=9, day-=20);   break; 
            case 12:(day<21)? (month=9, day+=10):(month=10, day-=20);  break; 
       default:          break; 
        } 
        } 
	if (y==3) { 
        year -= ( (month < 3) || ((month == 3) && (day < 21)) )? 622:621; 

        switch (month) { 
            case 1: (day<20)? (month=10, day+=11):(month=11, day-=19); break; 
            case 2: (day<19)? (month=11, day+=12):(month=12, day-=18); break; 
            case 3: (day<21)? (month=12, day+=10):(month=1, day-=20);   break; 
            case 4: (day<21)? (month=1, day+=11):(month=2, day-=20);   break; 
            case 5: 
            case 6: (day<22)? (month-=3, day+=10):(month-=2, day-=21); break; 
            case 7: 
            case 8: 
            case 9: (day<23)? (month-=3, day+=9):(month-=2, day-=22);  break; 
            case 10:(day<23)? (month=7, day+=8):(month=8, day-=22);    break; 
            case 11: 
            case 12:(day<22)? (month-=3, day+=9):(month-=2, day-=21);  break; 
       default:          break; 
        } 
        } 
		
	Today= a; 
	Today.setDate(day);
	Today.setMonth(month-1);
	Today.setYear(year);
	return Today;

}




function NewCal(pCtrl)
{
	dtToday=ConvertToSolar(dtToday);
	Cal=new Calendar(dtToday);
	pFormat="DDMMYYYY";
	
	if (pCtrl!=null)
		Cal.Ctrl=pCtrl;
		
	Cal.Format=pFormat.toUpperCase();
	
	exDateTime=document.getElementById(pCtrl).value;
	if (exDateTime!="")//Parse Date String
	{
		var Sp1;//Index of Date Separator 1
		var Sp2;//Index of Date Separator 2 
		var tSp1;//Index of Time Separator 1
		var tSp1;//Index of Time Separator 2
		var strMonth;
		var strDate;
		var strYear;
		var intMonth;
		var YearPattern;
		var strHour;
		var strMinute;
		var strSecond;
		//parse month
		Sp1=exDateTime.indexOf(DateSeparator,0)
		Sp2=exDateTime.indexOf(DateSeparator,(parseInt(Sp1)+1));

		if ((Cal.Format.toUpperCase()=="DDMMYYYY") || (Cal.Format.toUpperCase()=="DDMMMYYYY"))
		{
			strMonth=exDateTime.substring(Sp1+1,Sp2);
			strDate=exDateTime.substring(0,Sp1);
		}

		if (isNaN(strMonth)){
			intMonth=Cal.GetMonthIndex(strMonth);
		}
		else
			intMonth=parseInt(strMonth,10)-1;	
			
		if ((parseInt(intMonth,10)>=0) && (parseInt(intMonth,10)<12))
			Cal.Month=intMonth;

		//end parse month
		//parse Date
		if ((parseInt(strDate,10)<=Cal.GetMonDays()) && (parseInt(strDate,10)>=1))
			Cal.Date=strDate;

		//end parse Date
		//parse year
    
		strYear=exDateTime.substring(Sp2+1,Sp2+5);
   	YearPattern=/^\d{1,4}$/;
		if (YearPattern.test(strYear))
      if (parseInt(strYear,10)<100){
    		century=Math.floor(dtToday.getYear()/100);
        Cal.Year=parseInt(strYear,10)+century*100;
      }else 
        Cal.Year=parseInt(strYear,10);
        
		//end parse year
		
	}
	winCal=window.open("","DateTimePicker","toolbar=0,status=0,menubar=0,fullscreen=no,width=350,height=230,resizable=0,top="+cnTop+",left="+cnLeft);
	docCal=winCal.document;
	RenderCal();
}

function RenderCal()
{
	var vCalHeader;
	var vCalData;
	var vCalTime;
	var i;
	var j;
	var SelectStr;
	var vDayCount=0;
	var vFirstDay;

	docCal.open();
	docCal.writeln("<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><title>"+WindowTitle+"</title>");
	docCal.writeln("<link href='com/css/solar.css' rel='stylesheet' type='text/css'>");
	docCal.writeln("<script>var winMain=window.opener;</script>");
	docCal.writeln("</head><body link="+FontColor+" vlink="+FontColor+"><form name='Calendar'>");

	vCalHeader="<table dir='rtl' border=1 cellpadding=1 cellspacing=1 width='100%' align=\"center\" valign=\"top\">\n";
	//Month Selector
	vCalHeader+="<tr>\n<td colspan='7'><table border=0 width='100%' cellpadding=0 align=\"center\" cellspacing=0><tr><td>\n";
	vCalHeader+="<select name=\"MonthSelector\" onChange=\"javascript:winMain.Cal.SwitchMth(this.selectedIndex);winMain.RenderCal();\">\n";
	for (i=0;i<12;i++)
	{
		if (i==Cal.Month)
			SelectStr="Selected";
		else
			SelectStr="";	
		vCalHeader+="<option "+SelectStr+" value >"+MonthName[i]+"\n";
	}
	vCalHeader+="</select></td>";
	//Year selector
	vCalHeader+="\n<td><a href=\"javascript:winMain.Cal.DecYear();winMain.RenderCal()\"><b><font class=\"YrSelColor\"><</font></b></a><font class=\"YrSelColor\"><b> "+Cal.Year+" </b></font><a href=\"javascript:winMain.Cal.IncYear();winMain.RenderCal()\"><b><font class=\"YrSelColor\">></font></b></a></td></tr></table></td>\n";	
	vCalHeader+="</tr>";
	//Calendar header shows Month and Year
	if (ShowMonthYear)
		vCalHeader+="<tr><td colspan='7'><font class='MonthYearColor'><b>"+Cal.GetMonthName(ShowLongMonth)+" "+Cal.Year+"</b></font></td></tr>\n";
	//Week day header
	vCalHeader+="<tr class='WeekHeadColor'>";
	for (i=0;i<7;i++)
	{
		vCalHeader+="<td>"+WeekDayName[i]+"</td>";
	}
		
	vCalHeader+="</tr>";	
	docCal.write(vCalHeader);
	
	//Calendar detail
	vFirstDay=SolarToGrm(Cal.Year,Cal.Month,1);
		
	vCalData="<tr>";
	for (i=0;i<vFirstDay;i++)
	{
		vCalData=vCalData+GenCell();
		vDayCount=vDayCount+1;
	}
	for (j=1;j<=Cal.GetMonDays();j++)
	{
		var strCell;
		vDayCount=vDayCount+1;
		if ((j==dtToday.getDate())&&(Cal.Month==dtToday.getMonth())&&(Cal.Year==dtToday.getFullYear()))
			strCell=GenCell(j,true,"TodayColor");//Highlight today's date
		else
		{
			if (j==Cal.Date)
			{
				strCell=GenCell(j,true,"SelDateColor");
			}
			else
			{	 
				if (vDayCount%7==0)
					strCell=GenCell(j,false,"FridayColor");
				else
					strCell=GenCell(j,null,"WeekDayColor");
			}		
		}						
		vCalData=vCalData+strCell;

		if((vDayCount%7==0)&&(j<Cal.GetMonDays()))
		{
			vCalData=vCalData+"</tr>\n<tr>";
		}
	}
	docCal.writeln(vCalData);	
	docCal.writeln("\n</table>");
	docCal.writeln("</form></body></html>");
	docCal.close();
}

function GenCell(pValue,pHighLight,pColor)//Generate table cell with value
{
	var PValue;
	var PCellStr;
	var vColor;
	var vHLstr1;//HighLight string
	var vHlstr2;
	var vTimeStr;
	
	if (pValue==null)
		PValue="";
	else
		PValue=pValue;
	
	if (pColor!=null)
		vColor="class=\""+pColor+"\"";
	else
		vColor="";	
		
	if ((pHighLight!=null)&&(pHighLight))
		{vHLstr1="color='red'><b>";vHLstr2="</b>";}
	else
		{vHLstr1=">";vHLstr2="";}	

	PCellStr="<td "+vColor+" ><font face='verdana' size='2'"+vHLstr1+"<a href=\"javascript:winMain.document.getElementById('"+Cal.Ctrl+"').value='"+Cal.FormatDate(PValue)+"';window.close();\">"+PValue+"</a></font></td>";

	return PCellStr;
}

function Calendar(pDate,pCtrl)
{
	//Properties
	this.Date=pDate.getDate();//selected date
	this.Month=pDate.getMonth();//selected month number
	this.Year=pDate.getFullYear();//selected year in 4 digits
	this.MyWindow=winCal;
	this.Ctrl=pCtrl;
	this.Format="yyyymmdd";
	this.Separator=DateSeparator;
}

function GetMonthIndex(shortMonthName)
{
	for (i=0;i<12;i++)
	{
		if (MonthName[i].toUpperCase()==shortMonthName.toUpperCase())
				{	return i;}
	}
}
Calendar.prototype.GetMonthIndex=GetMonthIndex;

//increment year 1 point 
function IncYear()
{
	Cal.Year++;}
Calendar.prototype.IncYear=IncYear;

//decrement year 1 point 
function DecYear()
{	
	if(Cal.Year>0)
		Cal.Year--;	
}
Calendar.prototype.DecYear=DecYear;
	
function SwitchMth(intMth)
{	
	Cal.Month=intMth;
}
Calendar.prototype.SwitchMth=SwitchMth;

function GetMonthName(IsLong)
{
	var Month=MonthName[this.Month];
	if (IsLong)
		return Month;
	else
		return GetMonthIndex(Month);
}
Calendar.prototype.GetMonthName=GetMonthName;

function GetMonDays()//Get number of days in a month
{
	var DaysInMonth=[31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29];
	if (this.IsLeapYear())
	{
		DaysInMonth[11]=30;
	}	
	return DaysInMonth[this.Month];	
}
Calendar.prototype.GetMonDays=GetMonDays;

function IsLeapYear()
{
	if  (((((((this.Year - ((this.Year > 0) ? 474 : 473)) % 2820) + 474) + 38) * 682) % 2816) < 682)
		{
			return true;
	}
	else
	{
		return false;
	}
}
Calendar.prototype.IsLeapYear=IsLeapYear;

function FormatDate(pDate)
{
//insert zero before day digit
	if (pDate<10)
		tpDate="0"+pDate;
	else
		tpDate=pDate
    
 //insert zero before month digit   
	if (this.Month<9)
		tmonth="0"+(this.Month+1)
	else
		tmonth=this.Month+1
    

    if (this.Format.toUpperCase()=="DDMMYYYY")
							
      return (tpDate+DateSeparator+tmonth+DateSeparator+this.Year);
	
			
}

Calendar.prototype.FormatDate=FormatDate;	