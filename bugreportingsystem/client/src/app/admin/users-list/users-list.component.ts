import { Component, OnDestroy, OnInit } from '@angular/core';
import { FooterService } from 'src/app/auth/service/footer.service';
import { NavbarService } from 'src/app/auth/service/navbar.service';
interface User {
  email: string;
  bugsSubmitted: number;
  rewardsPoints: number;
}
@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit,OnDestroy{
  constructor(private navbarService:NavbarService,private footerService:FooterService){}
  users: User[] = [
    { email: 'user1@example.com', bugsSubmitted: 5, rewardsPoints: 100 },
    { email: 'user2@example.com', bugsSubmitted: 10, rewardsPoints: 200 }
    // Add more users as needed
  ];

  deleteUser(user: User): void {
    const index = this.users.indexOf(user);
    if (index !== -1) {
      this.users.splice(index, 1);
    }
  }
  ngOnInit(): void {
      this.navbarService.hide();
      this.footerService.hide();
  }
  ngOnDestroy(): void {
      
  }
  }
