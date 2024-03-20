import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './auth/signup/signup.component';
import { LoginComponent } from './auth/login/login.component';
import { ChangePasswordComponent } from './auth/change-password/change-password.component';
import { AboutComponent } from './about/about.component';
import { HomebodyComponent } from './homebody/homebody.component';
import { BugComponent } from './bug/bug/bug.component';
import { SuperadminComponent } from './super-admin/superadmin/superadmin.component';
import { AdminLoginComponent } from './admin/admin-login/admin-login.component';
import { SuperadminLoginComponent } from './super-admin/superadmin/superadmin-login/superadmin-login.component';
import { AuthGuard } from './auth/authguard';
import { AdminBugComponent } from './admin/admin-bug/admin-bug.component';
import { RegisterAdminComponent } from './super-admin/register-admin/register-admin.component';
import { AdminComponent } from './admin/admin/admin.component';
import { UsersListComponent } from './admin/users-list/users-list.component';
import { BugsPickedComponent } from './admin/bugs-picked/bugs-picked.component';
const routes: Routes = [
  {
    path: '',

    children: [
      { path: '', component: HomebodyComponent },
      { path: 'about', component: AboutComponent },
      { path: 'signup', component: SignupComponent },
      { path: 'login', component: LoginComponent },
    ],
  },
  {
    path: '',
    canActivate: [AuthGuard],
    children: [
      { path: 'changepassword', component: ChangePasswordComponent },
      { path: 'bug', component: BugComponent },
    ],
  },

  {
    path: 'admin',
    children: [
      { path: '', component: AdminLoginComponent },
      { path: 'userlist', component: UsersListComponent },
      { path: 'admindashboard', component: AdminComponent },
      { path: 'adminbug', component: AdminBugComponent },
      { path: 'buglist', component: BugsPickedComponent },
    ],
  },
  {
    path: 'super-admin',
    children: [
          { path: '', component: SuperadminLoginComponent },
          { path: '', component: SuperadminComponent },
          { path: 'userlist', component: UsersListComponent },
          { path: 'registeradmin', component: RegisterAdminComponent }
        ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
