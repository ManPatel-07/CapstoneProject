import { Component } from '@angular/core';

@Component({
  selector: 'app-dummy-profile',
  templateUrl: './dummy-profile.component.html',
  styleUrl: './dummy-profile.component.css'
})
export class DummyProfileComponent {
  activeSection: string = 'profile';

  showSection(section: string) {
    this.activeSection = section;
  }
}
