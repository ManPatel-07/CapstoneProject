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

  isEditing: boolean = false;
  
  aadharDetails = {
    name: 'Kushal Rajan',
    aadharNo: '0000 1111 2222',
    image: 'assets/img/aadhar.png'
  };

  toggleEdit() {
    this.isEditing = !this.isEditing;
  }

  saveAadharDetails() {
    this.isEditing = false;
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.aadharDetails.image = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }
}
