import { Component } from '@angular/core';
import { BugService } from 'src/app/bug/bug.service';
import { BuglistService } from './buglist.service';
import { NavbarService } from 'src/app/auth/service/navbar.service';
import { FooterService } from 'src/app/auth/service/footer.service';

@Component({
  selector: 'app-bugs-picked',
  templateUrl: './bugs-picked.component.html',
  styleUrls: ['./bugs-picked.component.css']
})
export class BugsPickedComponent {
  bugs!: any[];

  constructor(private bugService: BuglistService,private navbarService:NavbarService,private footerService:FooterService) { }

  ngOnInit(): void {
    this.bugService.getBugOfUser().subscribe(data => {
      this.bugs = data;
    });
    this.navbarService.hide();
    this.footerService.hide();
  }
}
