 <div class="panel-body">

                            <ul class="nav nav-pills nav-stacked">
                             <li>
                                    <a href="account"><i class="fa fa-user"></i> My Profile</a>
                                </li>
                                <li>
                                  
                                  <a href="addproduct"><i class=" fa fa-plus-circle"></i> Add Product</a>
                                </li>
  
                                
                                 <li>
                                    <a href="myproducts"><i class="fa fa-list"></i> My Products(${optionCount.get("myproducts")}) </a>
                                </li>
                                <li>
                                    <a href="orders-placed"><i class="fa fa-list"></i> Orders Placed (${optionCount.get("placedorders")})</a>
                                </li>
                                 <li>
                                    <a href="orders-received"><i class="fa fa-list"></i> Orders Received (${optionCount.get("receivedorders")})</a>
                                </li>
                                <li>
                                    <a href="wishlist"><i class="fa fa-heart"></i> My Wishlist(${optionCount.get("wishlistcount")})</a>
                                </li>
                                 <li>
                                    <a href="followers"><i class="fa fa-list"></i> My Followers(${optionCount.get("followerslistcount")})</a>
                                </li>
                                <li>
                                    <a href="followings"><i class="fa fa-list"></i> My Followings(${optionCount.get("followinglistcount")})</a>
                                </li>
                         		<li>
                                    <a href="inbox"><i class="fa fa-bell"></i>View Messages</a>
                                </li>
                                <li>
                                    <a href="changepassword"><i class="fa fa-user"></i> Change Password</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/logout""><i class="fa fa-sign-out"></i> Logout</a>
                                </li>
                            </ul>
                        </div>