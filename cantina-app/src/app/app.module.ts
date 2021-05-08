import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { NgPaymentCardModule } from 'ng-payment-card';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { LoginComponent } from './login/login.component';
import { ContactComponent } from './contact/contact.component';
import { AcasaComponent } from './acasa/acasa.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { RegisterFormComponent } from './register-form/register-form.component';
import {FormsModule} from "@angular/forms";
import { ParallaxImageComponent } from './parallax-image/parallax-image.component';
import { GoogleMapsModule } from '@angular/google-maps';
import { ContactFormComponent } from './contact-form/contact-form.component';
import { ContactInfoComponent } from './contact-info/contact-info.component'
import {HttpClientModule} from "@angular/common/http";
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AddProductComponent } from './add-product/add-product.component';
import { ProductsListComponent } from './menu/products-list.component';
import { AdminComponent } from './admin/admin.component';
import { EditProductComponent } from './table-edit-product/edit-product.component';
import { ProductSimpleComponent } from './product-simple/product-simple.component';
import { ProductComponent } from './product/product.component';
import { CartComponent } from './cart/cart.component';
import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { FilterPipe } from './filter.pipe';
import { ProductEditComponent } from './product-edit/product-edit.component';
import { CartItemComponent } from './cart-item/cart-item.component';
import { CheckoutComponent } from './checkout/checkout.component';
@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    LoginComponent,
    ContactComponent,
    AcasaComponent,
    LoginFormComponent,
    RegisterFormComponent,
    ParallaxImageComponent,
    ContactFormComponent,
    ContactInfoComponent,
    ForgetPasswordComponent,
    PageNotFoundComponent,
    AddProductComponent,
    ProductsListComponent,
    AdminComponent,
    EditProductComponent,
    ProductSimpleComponent,
    ProductComponent,
    CartComponent,
    FilterPipe,
    ProductEditComponent,
    CartItemComponent,
    CheckoutComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    GoogleMapsModule,
    HttpClientModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot(), // ToastrModule added
    NgPaymentCardModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
