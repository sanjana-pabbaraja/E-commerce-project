 <div id="footer" data-animate="fadeInUp">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-6">
                        <h4>Pages</h4>

                        <ul>
                            <li><a href="aboutus">About us</a>
                            </li>
                            <li><a href="termsandc">Terms and conditions</a>
                            </li>
                            <li><a href="FAQ">FAQ</a>
                            </li>
                            <li><a href="contact">Contact us</a>
                            </li>
                        </ul>

                        <hr>

                        <h4>User section</h4>

                        <ul>
                            <li><a href="#" data-toggle="modal" data-target="#login-modal">Login</a>
                            </li>
                            <li><a href="register">Register</a>
                            </li>
                        </ul>

                        <hr class="hidden-md hidden-lg hidden-sm">

                    </div>
                    <!-- /.col-md-3 -->

                    
                    <!-- /.col-md-3 -->

                    <div class="col-md-3 col-sm-6">

                        <h4>Where to find us</h4>

                        <p><strong>Marche</strong>
                            
                            <br>Hyderabad
                            <br>
                            <strong>India</strong>
                        </p>

                        <a href="contact">Go to contact page</a>

                        <hr class="hidden-md hidden-lg">

                    </div>
                    <!-- /.col-md-3 -->



                    <div class="col-md-3 col-sm-6">

                      
                      

                        <hr>

                        <h4>Stay in touch</h4>

                        <p class="social">
                            <a href="https://www.facebook.com/Marche-277765599430510/?view_public_for=277765599430510"  class="facebook external" data-animate-hover="shake"><i class="fa fa-facebook"></i></a>
                           
                            <a href="https://www.instagram.com/marchemerchant"  class="instagram external" data-animate-hover="shake"><i class="fa fa-instagram"></i></a>
                            <a href="mailto:marchemarket0@gmail.com? subject="  class="email external" data-animate-hover="shake"><i class="fa fa-envelope"></i></a>
                        </p>


                    </div>
                    <!-- /.col-md-3 -->

                </div>
                <!-- /.row -->

            </div>
            <!-- /.container -->
        </div>
        <!-- /#footer -->

        <!-- *** FOOTER END *** -->




        <!-- *** COPYRIGHT ***
 _________________________________________________________ -->
        <div id="copyright">
            <div class="container">
                <div class="col-md-6">
                    <p class="pull-left">© 2018 </p>

                </div>
                <div class="col-md-6">
                   
                </div>
            </div>
        </div>
        <!-- *** COPYRIGHT END *** -->
        
         <!-- /#all -->


    

    <!-- *** SCRIPTS TO INCLUDE ***
 _________________________________________________________ -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
    <script src="${pageContext.request.contextPath}/js/waypoints.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/modernizr.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-hover-dropdown.js"></script>
    <script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/front.js"></script>

<!-- NEW SCRIPTS -->

<!--DataTable Plugins-->
<script src="${pageContext.request.contextPath}/js/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/datatables/dataTables.bootstrap.min.js"></script>

<!-- DatePicker Plugin -->
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>

<!-- END OF NEW SCRIPTS -->    
    
    
    
<script>
function autocomplete(inp, arr) {
  /*the autocomplete function takes two arguments,
  the text field element and an array of possible autocompleted values:*/
  var currentFocus;
  /*execute a function when someone writes in the text field:*/
  inp.addEventListener("input", function(e) {
      var a, b, i, val = this.value;
      /*close any already open lists of autocompleted values*/
      closeAllLists();
      if (!val) { return false;}
      currentFocus = -1;
      /*create a DIV element that will contain the items (values):*/
      a = document.createElement("DIV");
      a.setAttribute("id", this.id + "autocomplete-list");
      a.setAttribute("class", "autocomplete-items");
      /*append the DIV element as a child of the autocomplete container:*/
      this.parentNode.appendChild(a);
      /*for each item in the array...*/
      for (i = 0; i < arr.length; i++) {
        /*check if the item starts with the same letters as the text field value:*/
        if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
          /*create a DIV element for each matching element:*/
          b = document.createElement("DIV");
          /*make the matching letters bold:*/
          b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
          b.innerHTML += arr[i].substr(val.length);
          /*insert a input field that will hold the current array item's value:*/
          b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
          /*execute a function when someone clicks on the item value (DIV element):*/
          b.addEventListener("click", function(e) {
              /*insert the value for the autocomplete text field:*/
              inp.value = this.getElementsByTagName("input")[0].value;
              /*close the list of autocompleted values,
              (or any other open lists of autocompleted values:*/
              closeAllLists();
          });
          a.appendChild(b);
        }
      }
  });
  /*execute a function presses a key on the keyboard:*/
  inp.addEventListener("keydown", function(e) {
      var x = document.getElementById(this.id + "autocomplete-list");
      if (x) x = x.getElementsByTagName("div");
      if (e.keyCode == 40) {
        /*If the arrow DOWN key is pressed,
        increase the currentFocus variable:*/
        currentFocus++;
        /*and and make the current item more visible:*/
        addActive(x);
      } else if (e.keyCode == 38) { //up
        /*If the arrow UP key is pressed,
        decrease the currentFocus variable:*/
        currentFocus--;
        /*and and make the current item more visible:*/
        addActive(x);
      } else if (e.keyCode == 13) {
        /*If the ENTER key is pressed, prevent the form from being submitted,*/
        e.preventDefault();
        if (currentFocus > -1) {
          /*and simulate a click on the "active" item:*/
          if (x) x[currentFocus].click();
        }
      }
  });
  function addActive(x) {
    /*a function to classify an item as "active":*/
    if (!x) return false;
    /*start by removing the "active" class on all items:*/
    removeActive(x);
    if (currentFocus >= x.length) currentFocus = 0;
    if (currentFocus < 0) currentFocus = (x.length - 1);
    /*add class "autocomplete-active":*/
    x[currentFocus].classList.add("autocomplete-active");
  }
  function removeActive(x) {
    /*a function to remove the "active" class from all autocomplete items:*/
    for (var i = 0; i < x.length; i++) {
      x[i].classList.remove("autocomplete-active");
    }
  }
  function closeAllLists(elmnt) {
    /*close all autocomplete lists in the document,
    except the one passed as an argument:*/
    var x = document.getElementsByClassName("autocomplete-items");
    for (var i = 0; i < x.length; i++) {
      if (elmnt != x[i] && elmnt != inp) {
        x[i].parentNode.removeChild(x[i]);
      }
    }
  }
  /*execute a function when someone clicks in the document:*/
  document.addEventListener("click", function (e) {
      closeAllLists(e.target);
      });
}

/*An array containing all the country names in the world:*/
var countries = ["Addagutta","Adikmet","AhmedNagar","Akberbagh","Allapur","AlwynColony","Amberpet","AMEERPET","AsifNagar","Attapur","Azampura","BaghAmberpet","Balajinagar","Balanagar","BanjaraHills","Bansilalpet","Barkas","BegumBazar","Begumpet","Bharathinagar","Bharathinagar","Bholakpur","BNReddyNagar","Borabanda","BoudhaNagar","Chaitanyapuri","Champapet","ChandaNagar","Chandrayangutta","Chawani","Cherlapalli","Chilukanagar","Chintal","Dabeerpura","Dattathreyanagar","Doodbowli","DrASRaoNagar","Erragadda","FALAKNUMA","FatheNagar","Gachibowli","Gaddiannaram","GajulaRamaram","Gandhinagar","GHANSIBAZAR","Golconda","Golnaka","GoshaMahal","Gowlipura","Gudimalkapur","Gunfoundry","Habsiguda","Hafeezpet","Hasthinapuram","Hayaatnagar","Himayathnagar","Hydernagar","ISSADAN","Jagadgirigutta","Jahanuma","Jambagh","Jangammet","Jeedimetla","JubileeHills","Kachiguda","Kanchanbagh","Kapra","Karwan","Kavadiguda","Khairtabad","Kishanbagh","Kondapur","Kothapet","KPHBColony","Kukatpally","Kurmaguda","Lalithbagh","langerhouse","Lingojiguda","Madhapur","Mallapur","Mallepally","Manghalhat","Mansoorabad","MeerpetHBColony","Mehdipatnam","Mettuguda","Miyapur","MOGHALPURA","MondaMarket","Moosapet","Moosrambagh","Musheerabad","Mylardevpally","Nacharam","Nagole","Nallakunta","Nanalnagar","NawabSahebKunta","OldBowenpally","OldMalakpet","Patancheruvu","PATHERGATTI","PURANAPUL","Qutbullapur","RahamathNagar","RajendraNagar","RamachandraPuram","RamaKrishnaPuram","Ramanthapur","RamgopalPet","Ramnagar","Ramnaspura","Rangareddynagar","RedHills","ReinBazar","RiyasathNagar","Saidabad","Sanathnagar","SanthoshNagar","Saroornagar","Seethaphalmandi","Serilingampally","Shaikhpet","SHALIBANDA","Shastripuram","Somajiguda","Subhashnagar","SulemanNagar","Suraram","Talabchanchalam","Tarnaka","Tolichowki","Uppal","Uppuguda","Vanasthalipuram","VengalraoNagar","VENKATESHWARACOLONY","VijayanagarColony","VivekanandaNagarColony","Yousufguda","Ziaguda"];

/*initiate the autocomplete function on the "myInput" element, and pass along the countries array as possible autocomplete values:*/
autocomplete(document.getElementById("myInput"), countries);

var check = function() {
	   if (document.getElementById('newpwd').value ==
	     document.getElementById('cnewpwd').value) {
		  
        document.getElementById('submit').disabled = false;

	     
	   } else {
	        document.getElementById('submit').disabled = true;
	   }
	 }

</script>


<!-- Searching Script -->
<script>
function autocompletesearch(inp, arr) {
  /*the autocomplete function takes two arguments,
  the text field element and an array of possible autocompleted values:*/
  var currentFocus;
  /*execute a function when someone writes in the text field:*/
  inp.addEventListener("input", function(e) {
      var a, b, i, val = this.value;
      /*close any already open lists of autocompleted values*/
      closeAllLists();
      if (!val) { return false;}
      currentFocus = -1;
      /*create a DIV element that will contain the items (values):*/
      a = document.createElement("DIV");
      a.setAttribute("id", this.id + "autocompletesearch-list");
      a.setAttribute("class", "autocompletesearch-items");
      /*append the DIV element as a child of the autocomplete container:*/
      this.parentNode.appendChild(a);
      /*for each item in the array...*/
      for (i = 0; i < arr.length; i++) {
        /*check if the item starts with the same letters as the text field value:*/
        if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
          /*create a DIV element for each matching element:*/
          b = document.createElement("DIV");
          /*make the matching letters bold:*/
          b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
          b.innerHTML += arr[i].substr(val.length);
          /*insert a input field that will hold the current array item's value:*/
          b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
          /*execute a function when someone clicks on the item value (DIV element):*/
          b.addEventListener("click", function(e) {
              /*insert the value for the autocomplete text field:*/
              inp.value = this.getElementsByTagName("input")[0].value;
              /*close the list of autocompleted values,
              (or any other open lists of autocompleted values:*/
              closeAllLists();
          });
          a.appendChild(b);
        }
      }
  });
  /*execute a function presses a key on the keyboard:*/
  inp.addEventListener("keydown", function(e) {
      var x = document.getElementById(this.id + "autocompletesearch-list");
      if (x) x = x.getElementsByTagName("div");
      if (e.keyCode == 40) {
        /*If the arrow DOWN key is pressed,
        increase the currentFocus variable:*/
        currentFocus++;
        /*and and make the current item more visible:*/
        addActive(x);
      } else if (e.keyCode == 38) { //up
        /*If the arrow UP key is pressed,
        decrease the currentFocus variable:*/
        currentFocus--;
        /*and and make the current item more visible:*/
        addActive(x);
      } else if (e.keyCode == 13) {
        /*If the ENTER key is pressed, prevent the form from being submitted,*/
        e.preventDefault();
        if (currentFocus > -1) {
          /*and simulate a click on the "active" item:*/
          if (x) x[currentFocus].click();
        }
      }
  });
  function addActive(x) {
    /*a function to classify an item as "active":*/
    if (!x) return false;
    /*start by removing the "active" class on all items:*/
    removeActive(x);
    if (currentFocus >= x.length) currentFocus = 0;
    if (currentFocus < 0) currentFocus = (x.length - 1);
    /*add class "autocomplete-active":*/
    x[currentFocus].classList.add("autocompletesearch-active");
  }
  function removeActive(x) {
    /*a function to remove the "active" class from all autocomplete items:*/
    for (var i = 0; i < x.length; i++) {
      x[i].classList.remove("autocompletesearch-active");
    }
  }
  function closeAllLists(elmnt) {
    /*close all autocomplete lists in the document,
    except the one passed as an argument:*/
    var x = document.getElementsByClassName("autocompletesearch-items");
    for (var i = 0; i < x.length; i++) {
      if (elmnt != x[i] && elmnt != inp) {
        x[i].parentNode.removeChild(x[i]);
      }
    }
  }
  /*execute a function when someone clicks in the document:*/
  document.addEventListener("click", function (e) {
      closeAllLists(e.target);
      });
}

/*An array containing all the Product categories */
var products = ["Nokia 7 plus","Nokia Lumia 540","Nokia Lumia 1020","Samsung Galaxy J7","Samsung Galaxy J5","Samsung Galaxy J3","Iphone X","Iphone 6s","Iphone 4S","Honor 9 Lite","Huawei Honor 8","Huawei Honor 4X","Philips_SHL3000BK Headband Headphone","Bose SoundSport Wireless Headphones","Pioneer SE-MJ561BT-S Bluetooth Wireless Headphones with NFC Connectivity.","Philips BT50B Portable Wireless Bluetooth Speaker.","JBL GO Portable Wireless Bluetooth Speaker with Mic ","Sony Extra Bass SRS-XB10 Portable Splash-proof Wireless Speakers with Bluetooth and NFC.","Bluetooth Selfie Remote Shutter Control","Nikon D3400 Digital Camera.","Sony Alpha DSLR-A850 Body.","Samsung 100 cm (40 inches) 40M5100 Basic Smart Full HD LED TV","BlackOx 55 Ultra HD UHD 4K Smart Android LED TV.","Samsung 48J6300 121 cm (48 inches) Full HD Curved Smart LED Television.","AnjuShree Choice Women Stitched Printed Cotton Anarkali Kurti.","Kesari King Womens Cotton Saree ","Vastrang Womens Georgette Anarkali Traditional Gown For Party Wear and Festival_Glossy.","Adexe The Meek Leather Womens Watch","Bulova"];
products=products.sort();
/*initiate the autocomplete function on the "mySearch" element, and pass along the products array as possible autocomplete values:*/
autocompletesearch(document.getElementById("mySearch"), products);

</script>
<!-- End For Searching Script -->



<!-- NEW SCRIPT FUNCTIONALITIES -->

<script type="text/javascript">

    $(document).ready(function() {
    	
    	$(".label-info:contains('Order-Placed')").css('background-color','#5bc0de');
    	$(".label-info:contains('Order-Closed')").css('background-color','#5cb85c');
    	$(".label-info:contains('In-Progress')").css('background-color','#f0ad4e');
    	$(".label-info:contains('Order-Confirmed')").css('background-color','#f0ad4e');
    	$(".label-info:contains('Pickedup from Seller')").css('background-color','#f0ad4e');
    	$(".label-info:contains('Delivered')").css('background-color','#5cb45c');
    	$(".label-info:contains('Order-Completed')").css('background-color','green');

    	
    	
    	
    	  
    	  
    	  
    	
    	
    	
    	var $divs = $("div.prod");
    	
    	$('#sort').on('change', function(event) {
          
    		if(this.value==1){
    			 var alphabeticallyOrderedDivs = $divs.sort(function (a, b) {
    	    	        return $(a).find(".pname").text() > $(b).find(".pname").text();
    	    	    });
    	    	    $(".products").html(alphabeticallyOrderedDivs);
    		}
    		if(this.value==2){
    			  var numericallyOrderedDivs = $divs.sort(function (a, b) {
    				//  var val = parseFloat($(".price").text().replace('$',''));
    	    	        return parseInt($(a).find(".price").text().replace('$','')) > parseInt($(b).find(".price").text().replace('$',''));
    	    	    });
    	    	    $(".products").html(numericallyOrderedDivs);
    		}
    		
    		if(this.value==3){
  			  var numericallyOrderedDivs = $divs.sort(function (a, b) {
  	    	        return $(a).find(".price").text() > $(b).find(".price").text();
  	    	    });
  	    	    $(".products").html(numericallyOrderedDivs);
  		}
        });

    	
    	$(".star").click(function() {
    		//alert("zxc");
    		$('.ratebtn').prop('disabled', false);
    		});
    	
    	
        function checkCategory(that) {
//        	alert(that.value)
            if (that.value) {
            	window.location.href = "category";
            } 
        }
        
        $('#datePickerFrom').datepicker({
            format: 'dd/mm/yyyy',
//            maxDate: new Date()
            endDate: '+0d',
            autoclose: true
        });

        $('#dataTables').DataTable({
            searching: true,
            bFilter: true,
            paging: true,
            responsive: true,
//            "sDom":"lrtip" ,
            language: {
                search: "_INPUT_",
                searchPlaceholder: "Search records",
            }
        });
        var oTable;
        oTable = $('#dataTables').dataTable();

        $('#tableFilter').change( function() { 
        	
        	if($(this).val() == "Select"){
                oTable.fnFilter("");         		
        	}
        	else{
              oTable.fnFilter( $(this).val() ); 
        	}
              
         });
        
        $('#datePickerFrom').on('changeDate', function(event) {
            var date = event.format();
            oTable.fnFilter(date); 
        });

    });
</script>


<!--END NEW SCRIPT FUNCTIONALITIES -->

</body>
        