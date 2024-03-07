import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './auth/signup/signup.component';
import { LoginComponent } from './auth/login/login.component';
import { ChangePasswordComponent } from './auth/change-password/change-password.component';
import { AboutComponent } from './about/about.component';
import { HomebodyComponent } from './homebody/homebody.component';

const routes: Routes = [
  { path:'', component: HomebodyComponent},
  { path:'signup', component: SignupComponent},
  {path: 'login', component: LoginComponent},
  {path:'changepassword',component:ChangePasswordComponent},
  {path:'about',component:AboutComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
