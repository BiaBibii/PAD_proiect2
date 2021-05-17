import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'tr[app-product-order]',
  templateUrl: './product-order.component.html',
  styleUrls: ['./product-order.component.css']
})
export class ProductOrderComponent implements OnInit {

  @Input()
  item:any;

  constructor() { }

  ngOnInit(): void {
  }

}
