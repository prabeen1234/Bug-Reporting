import { Component } from '@angular/core';
import { Subscription } from 'rxjs';
import { FooterService } from '../auth/service/footer.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {

  showFooter!:boolean;
  subscription:Subscription;

  constructor(private footerService:FooterService){
    this.subscription = footerService.showFooter.subscribe((value)=>{
      this.showFooter = value;
    });
  }
ngOnDestroy(): void {
  this.subscription.unsubscribe;
    
}

}
