import { Component, OnInit } from '@angular/core';
import { AdminbarService } from '../admin/admin/adminbar.service';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {
  constructor (private adminbarService:AdminbarService){}
  ngOnInit(): void {
      this.adminbarService.hide();
  }

}
