import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MenubarModule } from 'primeng/menubar';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { HomebodyComponent } from './homebody/homebody.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SignupComponent } from './auth/signup/signup.component';
import { FooterComponent } from './footer/footer.component';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ChangePasswordComponent } from './auth/change-password/change-password.component';
import { PasswordModule } from 'primeng/password';
import { AboutComponent } from './about/about.component';

import { SharedService } from './shared/shared.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomebodyComponent,
    NavbarComponent,
    SignupComponent,
    FooterComponent,
    ChangePasswordComponent,
    AboutComponent

  ],
  imports: [
    BrowserModule,PasswordModule,
    AppRoutingModule,
    MenubarModule,
    ToastrModule.forRoot({
      timeOut: 1000,
      positionClass: 'toast-bottom-right',
      preventDuplicates: true,
    }),
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
