import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FooterService {
showFooter:BehaviorSubject<boolean>;
  constructor() {
    this.showFooter =new BehaviorSubject(true);
   }
   hide(){
    this.showFooter.next(false);
   }
   show(){
    this.showFooter.next(true);
   }
}
