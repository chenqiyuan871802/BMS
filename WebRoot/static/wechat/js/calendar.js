var calUtil = {
  //当前日历显示的年份
  showYear:2015,
  //当前日历显示的月份
  showMonth:1,
  //当前日历显示的天数
  showDays:1,
  eventName:"load",
  //初始化日历
  init:function(signList){
    calUtil.setMonthAndDay();
    calUtil.draw(signList);
    calUtil.bindEnvent();
  },
  draw:function(signList){
    //绑定日历
    var str = calUtil.drawCal(calUtil.showYear,calUtil.showMonth,signList);
    $("#calendar").html(str);
    //绑定日历表头
    var calendarName=calUtil.showYear+"年"+calUtil.showMonth+"月";
    $(".calendar_month_span").html(calendarName);
   

    /*弹出时间段选择*/
    $('#calendar th.on').on('click',function(){
        $('.popu-time,.mask').show();
        var checkedDay = $(this).html();
        $('.popup-hd-date').html(calUtil.showMonth+"月"+checkedDay+"日")
        $('#service_date').val(calUtil.showYear+"-"+calUtil.showMonth+"-"+checkedDay)
        
        var tdIndex = $('#calendar th').index(this);
        if(tdIndex%7==0){
          tdIndex = 0;
        }
        else if(tdIndex%7==1){
          tdIndex = 1;
        }
        else if(tdIndex%7==2){
          tdIndex = 2;
        }
        else if(tdIndex%7==3){
          tdIndex = 3;
        }
        else if(tdIndex%7==4){
          tdIndex = 4;
        }
        else if(tdIndex%7==5){
          tdIndex = 5;
        }
        else if(tdIndex%7==6){
          tdIndex = 6;
        }
        var weekArray = new Array("日","一","二","三","四","五","六");
        var checkedWeek = '星期' +  weekArray[tdIndex];
        $('.popup-hd-week').html(checkedWeek)
        $('#service_week').val(checkedWeek)
        showSubscribeTime();
    })  
  },

  //绑定事件
  bindEnvent:function(){
    //绑定上个月事件
    var date = new Date();
    var nowDays = date.getDate();
    var nowMonth = date.getMonth() + 1;
    var days = new Array("day0","day1","day2","day3","day4","day5","day6");
    if(calUtil.showMonth==date.getMonth()+2){
      $(".calendar_month_prev").click(function(){
        if(nowMonth == 2){
          for(i=0;i<7;i++){
                if(days[i-1] == 31){
                   break;
                }
                else{
                    days[i]= nowDays + i;
                }
            }
        }
        else if (nowMonth == 1 || nowMonth == 3 || nowMonth == 5 || nowMonth == 7 || nowMonth == 8 || nowMonth == 10 || nowMonth == 12){
            for(i=0;i<7;i++){
                if(days[i-1] == 31){
                   break;
                }
                else{
                    days[i]= nowDays + i;
                }
            }
        }
        else{
            for(i=0;i<7;i++){
                if(days[i-1] == 30){
                   break;
                }
                else{
                    days[i]= nowDays + i;
                }
            }
        } 
        var signList=[
            {"signDay":days[0]},
            {"signDay":days[1]},
            {"signDay":days[2]},
            {"signDay":days[3]},
            {"signDay":days[4]},
            {"signDay":days[5]},
            {"signDay":days[6]}
        ];
        calUtil.eventName="prev";
        calUtil.init(signList);
        $('#calendar th.on').eq(0).addClass('today')
        $('#calendar th.on').eq(1).addClass('tomorrow')
        $('#calendar th.on').eq(2).addClass('bermorgen')
        
      });
    }
    //绑定下个月事件
    if(calUtil.showMonth==date.getMonth()+1){
      $(".calendar_month_next").click(function(){
        if(nowMonth == 2){
          if(nowDays > 22){
            var num =7-(29-nowDays);
            for(i=0;i<num;i++){
                days[i]= i+1;
            }
            nextCal()
          }
        }
        else if (nowMonth == 1 || nowMonth == 3 || nowMonth == 5 || nowMonth == 7 || nowMonth == 8 || nowMonth == 10 || nowMonth == 12){
            if(nowDays > 24){
              var num =7-(32-nowDays);
              for(i=0;i<num;i++){
                  days[i]= i+1;
              }
              nextCal()
            }
        }
        else{
          if(nowDays > 24){
            var num =7-(31-nowDays);
            for(i=0;i<num;i++){
                days[i]= i+1;
            }
            nextCal()
          }
        }
        function nextCal(){
          var signList=[
              {"signDay":days[0]},
              {"signDay":days[1]},
              {"signDay":days[2]},
              {"signDay":days[3]},
              {"signDay":days[4]},
              {"signDay":days[5]},
              {"signDay":days[6]}
          ];
          calUtil.eventName="next";
          calUtil.init(signList);
          if($('#calendar th.on').length == 5 ){
            $('#calendar th.on').eq(0).addClass('bermorgen')
          }
          else if($('#calendar th.on').length == 6 ){
            $('#calendar th.on').eq(0).addClass('tomorrow')
            $('#calendar th.on').eq(1).addClass('bermorgen')
          }
        }  
      });
    }
  },
  //获取当前选择的年月
  setMonthAndDay:function(){
    switch(calUtil.eventName)
    {
      case "load":
        var current = new Date();
        calUtil.showYear=current.getFullYear();
        calUtil.showMonth=current.getMonth() + 1;
        break;
      case "prev":
        var nowMonth=$(".calendar_month_span").html().split("年")[1].split("月")[0];
        calUtil.showMonth=parseInt(nowMonth)-1;
        if(calUtil.showMonth==0)
        {
            calUtil.showMonth=12;
            calUtil.showYear-=1;
        }
        break;
      case "next":
        var nowMonth=$(".calendar_month_span").html().split("年")[1].split("月")[0];
        calUtil.showMonth=parseInt(nowMonth)+1;
        if(calUtil.showMonth==13)
        {
            calUtil.showMonth=1;
            calUtil.showYear+=1;
        }
        break;
    }
  },
  getDaysInmonth : function(iMonth, iYear){
   var dPrevDate = new Date(iYear, iMonth, 0);
   return dPrevDate.getDate();

  },
  bulidCal : function(iYear, iMonth) {
   var aMonth = new Array();
   aMonth[0] = new Array(7);
   aMonth[1] = new Array(7);
   aMonth[2] = new Array(7);
   aMonth[3] = new Array(7);
   aMonth[4] = new Array(7);
   aMonth[5] = new Array(7);
   aMonth[6] = new Array(7);
   var dCalDate = new Date(iYear, iMonth - 1, 1);
   var iDayOfFirst = dCalDate.getDay();
   var iDaysInMonth = calUtil.getDaysInmonth(iMonth, iYear);
   var iVarDate = 1;
   var d, w;
   aMonth[0][0] = "日";
   aMonth[0][1] = "一";
   aMonth[0][2] = "二";
   aMonth[0][3] = "三";
   aMonth[0][4] = "四";
   aMonth[0][5] = "五";
   aMonth[0][6] = "六";
   for (d = iDayOfFirst; d < 7; d++) {
    aMonth[1][d] = iVarDate;
    iVarDate++;
   }
   for (w = 2; w < 7; w++) {
    for (d = 0; d < 7; d++) {
     if (iVarDate <= iDaysInMonth) {
      aMonth[w][d] = iVarDate;
      iVarDate++;
     }
    }
   }
   return aMonth;
  },
  ifHasSigned : function(signList,day){
   var signed = false;
   $.each(signList,function(index,item){
    if(item.signDay == day) {
     signed = true;
     return false;
    }
   });
   return signed ;
  },
  drawCal : function(iYear, iMonth ,signList) {
   var myMonth = calUtil.bulidCal(iYear, iMonth);
   var htmls = new Array();
   htmls.push("<div class='sign_main' id='sign_layer'>");
   htmls.push("<div class='sign_succ_calendar_title'>");
   htmls.push("<div class='calendar_month_prev'><</div>");
   htmls.push("<div class='calendar_month_span'></div>");
   htmls.push("<div class='calendar_month_next'>></div>");
   htmls.push("</div>");
   htmls.push("<div class='sign' id='sign_cal'>");
   htmls.push("<table>");
   htmls.push("<tr>");
   htmls.push("<th>" + myMonth[0][0] + "</th>");
   htmls.push("<th>" + myMonth[0][1] + "</th>");
   htmls.push("<th>" + myMonth[0][2] + "</th>");
   htmls.push("<th>" + myMonth[0][3] + "</th>");
   htmls.push("<th>" + myMonth[0][4] + "</th>");
   htmls.push("<th>" + myMonth[0][5] + "</th>");
   htmls.push("<th>" + myMonth[0][6] + "</th>");
   htmls.push("</tr>");
   var d, w;
   for (w = 1; w < 7; w++) {
    htmls.push("<tr>");
    for (d = 0; d < 7; d++) {
     var ifHasSigned = calUtil.ifHasSigned(signList,myMonth[w][d]);
     console.log(ifHasSigned);
     if(ifHasSigned){
      htmls.push("<th class='on'>" + (!isNaN(myMonth[w][d]) ? myMonth[w][d] : " ") + "</th>");
     } else {
      htmls.push("<th>" + (!isNaN(myMonth[w][d]) ? myMonth[w][d] : "<span></span>") + "</td>");
     }
    }
    htmls.push("</tr>");
   }
   htmls.push("</table>");
   htmls.push("</div>");
   htmls.push("</div>");
   return htmls.join('');
  }
};