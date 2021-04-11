import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


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
    ContactInfoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    GoogleMapsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
