import { Component, OnInit } from '@angular/core';
import { SignupService } from '../service/signup.service';
import { ToastrService } from 'ngx-toastr';
import { RegisterUser } from 'src/app/user';
import { AdminbarService } from 'src/app/admin/admin/adminbar.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  constructor(private registerService: SignupService,private toast:ToastrService ,private adminbarService:AdminbarService){}
  public data: RegisterUser = {
    firstName: '',
    lastName: '',
    email: '',
    password: '',
  };
    saveUser() {
    this.registerService.registerUser(this.data).subscribe({
      next:(response)=>{
         this.toast.success("successfully register")
      },
      error:(error)=>{
          this.toast.error(error)
          console.log("error")
      }
    })
}
ngOnInit(): void {
    this.adminbarService.hide();
}
}
