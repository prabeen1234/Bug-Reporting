import { Component, OnInit } from '@angular/core';
import { SharedService } from '../shared/shared.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homebody',
  templateUrl: './homebody.component.html',
  styleUrls: ['./homebody.component.css']
})
export class HomebodyComponent implements OnInit{
  
  constructor(private sharedService: SharedService, private router: Router) { }

  isLoggedIn: boolean = false; 

  ngOnInit() {
      this.sharedService.loginStatus$.subscribe((status) => {
      this.isLoggedIn = status;
    });
  }

  navigateToBugForm() {
    if (this.isLoggedIn) {
      this.router.navigate(['/bug']);
    } else {
      this.router.navigate(['/login']); 
    }
  }

}
