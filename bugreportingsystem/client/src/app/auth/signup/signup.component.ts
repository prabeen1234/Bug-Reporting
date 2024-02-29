import { Component } from '@angular/core';
import { SignupService } from '../service/signup.service';
import { ToastrService } from 'ngx-toastr';
import { RegisterUser } from 'src/app/user';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  constructor(private registerService: SignupService,private toast:ToastrService){}
  public data: RegisterUser = {
    firstName: '',
    lastName: '',
    email: '',
    password: '',
  };
    saveUser() {
    this.registerService.registerUser(this.data).subscribe({
      next:(response)=>{
        console.log("successfully")
         this.toast.success("successfully register")
      },
      error:(error)=>{
          this.toast.error(error)
          console.log("error")
      }
    })
}
}
