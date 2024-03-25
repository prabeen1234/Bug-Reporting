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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ChangePasswordComponent } from './auth/change-password/change-password.component';
import { PasswordModule } from 'primeng/password';
import { AboutComponent } from './about/about.component';
import { ButtonModule } from 'primeng/button';
import { SidebarModule } from 'primeng/sidebar';
import { DropdownModule } from 'primeng/dropdown';
import { BugComponent } from './bug/bug/bug.component';
import { AdminComponent } from './admin/admin/admin.component';
import { AdminBugComponent } from './admin/admin-bug/admin-bug.component';
import { SuperadminComponent } from './super-admin/superadmin/superadmin.component';
import { JwtModule } from '@auth0/angular-jwt';
import { SuperadminLoginComponent } from './super-admin/superadmin/superadmin-login/superadmin-login.component';
import { SharedService } from './shared/shared.service';
import { AuthGuard } from './auth/authguard';
import { RegisterAdminComponent } from './super-admin/register-admin/register-admin.component';
import { UsersListComponent } from './admin/users-list/users-list.component';
import { BugsPickedComponent } from './admin/bugs-picked/bugs-picked.component';
export function tokenGetter() {
  return localStorage.getItem("access_token");
}
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomebodyComponent,
    NavbarComponent,
    SignupComponent,
    FooterComponent,
    ChangePasswordComponent,
    AboutComponent,
    BugComponent,
    AdminComponent,
    AdminBugComponent,
    SuperadminComponent,
    SuperadminLoginComponent,
    RegisterAdminComponent,
    UsersListComponent,
    BugsPickedComponent
  ],
  imports: [
    BrowserModule,
    PasswordModule,
    AppRoutingModule,
    MenubarModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter

      },
    }),
    ToastrModule.forRoot({
      timeOut: 1000,
      positionClass: 'toast-bottom-right',
      preventDuplicates: true,
    }),
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    DropdownModule,
    ReactiveFormsModule,
    SidebarModule,
    ButtonModule,
  ],
  providers: [SharedService,AuthGuard],
  bootstrap: [AppComponent],
})
export class AppModule {}
