import { Component, OnInit,Input } from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {
  img: String;
  constructor() {
    this.img="/assets/img/contact_top.jpg"
  }

  ngOnInit(): void {
  }

}
