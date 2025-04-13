import { AfterViewInit, Component, ElementRef, Renderer2, ViewChild } from '@angular/core';
import { AadharData, AadharService } from '../../services/aadhar.service';

declare var bootstrap: any; // Declare Bootstrap globally if needed

@Component({
  selector: 'app-test-ui',
  templateUrl: './test-ui.component.html',
  styleUrl: './test-ui.component.css'
})
export class TestUiComponent {
  aadharData = {
    aadharName: '',
    aadharNo: '',
  };
  selectedFile!: File;

  constructor(private aadharService: AadharService) {}

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  submitForm() {
    if (this.selectedFile && this.aadharData.aadharName && this.aadharData.aadharNo) {
      this.aadharService.uploadAadhar(this.aadharData, this.selectedFile).subscribe(
        (response) => {
          alert('🎉 Aadhaar uploaded successfully!');
        },
        (error) => {
          alert('❌ Error uploading Aadhaar!');
        }
      );
    } else {
      alert('⚠️ Please fill in all fields and select an image.');
    }
  }
}
