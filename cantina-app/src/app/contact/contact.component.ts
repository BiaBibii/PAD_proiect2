import { Component, OnInit,Input } from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {
  mapOptions: google.maps.MapOptions = {
    center: { lat: 45.748204346166304, lng: 21.23992131693315 },
    zoom : 17
  };
  marker = {
    position: { lat: 45.748204346166304, lng: 21.23992131693315 },
  };

  img: String;
  constructor() {
    this.img="/assets/img/contact_top.jpg"
  }

  ngOnInit(): void {
  }

}
