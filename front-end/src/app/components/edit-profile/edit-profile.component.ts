import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {User} from "../../model/model.user";
import {Router} from "@angular/router";
import {AccountService} from "../../services/account.service";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class EditProfileComponent implements OnInit {
  currentUser: User;
  user: User = new User();
  errorMessage: string;

  constructor(public accountService: AccountService, public router: Router) {

      this.currentUser = JSON.parse(localStorage.getItem('currentUser'));



  }

  ngOnInit() {

  }

  updateUser() {
    this.accountService.editAccount(this.user).subscribe(data => {
        this.router.navigate(['/login']);
      }, err => {
        console.log(err);
        this.errorMessage = "Inp cor parameters";
      }
    )
  }
}
