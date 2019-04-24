import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {User} from "../../model/model.user";
import {AccountService} from "../../services/account.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css'],
  encapsulation: ViewEncapsulation.None

})
export class ChangePasswordComponent implements OnInit {

  user: User = new User();
  errorMessage: string;

  constructor(public accountService: AccountService, public router: Router) {
  }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'))
  }

  updateUser() {
    this.accountService.editAccount(this.user).subscribe(data => {
        this.router.navigate(['/']);
      }, err => {
        console.log(err);
        this.errorMessage = "Bad parameters";
      }
    )
  }
}
