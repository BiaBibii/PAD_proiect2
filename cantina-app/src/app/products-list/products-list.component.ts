import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css']
})
export class ProductsListComponent implements OnInit {
  img="/assets/img/";

  //categoryes = soup, garnish, meat, extra, drinks
  categories=["SOUP", "PASTA", "MEAT","FISH","DESSERT","DRINKS"];



  constructor() { }

  ngOnInit(): void {
  }

}
