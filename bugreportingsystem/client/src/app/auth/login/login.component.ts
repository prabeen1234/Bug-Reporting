import { Component } from '@angular/core';
import { LoginService } from '../service/login.service';
import { ToastrService } from 'ngx-toastr';
import { LoginUser } from 'src/app/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  public data: LoginUser = {
    email: '',
    password: '',
  };
constructor(private loginService:LoginService,private toast:ToastrService){}
onSubmit() {
    this.loginService.loginUser(this.data).subscribe({
      next:(response) => {
        this.toast.success('successfully login');
        localStorage.setItem(
          'currentUser',
          JSON.stringify({ token: response, name: name })
        );
      },
      error:(error)=>{
           this.toast.error("user not found");
      }
      
    })
  }

}
