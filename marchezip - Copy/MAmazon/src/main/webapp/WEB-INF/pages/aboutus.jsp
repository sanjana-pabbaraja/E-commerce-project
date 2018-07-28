<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />

    <div id="all">

        <div id="content">
            <div class="container">

                <div class="col-md-12">
                    <ul class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}/index">Home</a>
                        </li>
                        <li>Contact</li>
                    </ul>

                </div>

                <div class="col-md-3">
                    <!-- *** PAGES MENU ***
 _________________________________________________________ -->
                    <div class="panel panel-default sidebar-menu">

                        <div class="panel-heading">
                            <h3 class="panel-title">Pages</h3>
                        </div>

                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <a href="termsandc.jpg">Terms And Condoitions</a>
                                </li>
                                <li>
                                    <a href="contact.jpg">Contact page</a>
                                </li>
                                <li>
                                    <a href="FAQ.jpg">FAQ</a>
                                </li>

                            </ul>

                        </div>
                    </div>

                    <!-- *** PAGES MENU END *** -->


                  <div class="banner">
                        <a href="#">
                            <img src="img/banner.jpg" alt="sales 2014" class="img-responsive">
                        </a>
                    </div> 
                </div>

                <div class="col-md-9">


                    <div class="box" id="contact">
                        <h1>About us</h1>

                        <p ><b>Marche is an application for simplifying peer-to-peer selling and buying new or used products like fashion, mobile, books
                        and many more. It also enables the user to search the products according to category, location and sub-category. It has functionalities
                        like wish list, add to cart and provides a hustle free checkout through Amazon pay or cash on delivery option. 
                        </b></p>

                        


                    </div>


                </div>
                <!-- /.col-md-9 -->
            </div>
            <!-- /.container -->
        </div>
        <!-- /#content -->


        <!-- *** FOOTER ***
 _________________________________________________________ -->
       <jsp:include page="footer.jsp" />
       


    </div>
    <!-- /#all -->


    

   


</body>

</html>