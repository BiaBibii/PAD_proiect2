import { Component, OnInit } from '@angular/core';
import {not} from "rxjs/internal-compatibility";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {



  constructor() { }

  ngOnInit(): void {

  }


  processPayment() {
    console.log("mergeee");
  }

  submit() {

  }
}
