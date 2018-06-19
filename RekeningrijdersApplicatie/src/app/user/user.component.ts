import { Component, OnInit, Input } from '@angular/core';
import { User } from '../_model/User';
import { TranslateService } from '@ngx-translate/core';
import { HttpService } from '../_services/http.service';
import { CommonService } from '../_services/common.service';

@Component({
    selector: 'app-user',
    templateUrl: 'user.component.html',
    styleUrls: ['user.component.css']
})

export class UserComponent implements OnInit {
    @Input() user: User;

    passwordAlerts: string[] = [];

    userData = {
        email: '',
        address: '',
        oldPassword: '',
        newPassword: '',
        repNewPassword: '',
    };

    constructor(
        public translate: TranslateService,
        private http: HttpService,
        public common: CommonService
    ) {
    }

    ngOnInit() {
        this.resetData();
    }

    resetData() {
        this.userData.email = this.user.email;
        this.userData.address = this.user.address;
        this.passwordAlerts = [];
    }

    saveData(userdata) {
        this.passwordAlerts = [];
        if (!this.checkPassword()) {
            return null;
        }
        const updatedUser = { email: this.userData.email, address: this.userData.address, password: this.userData.newPassword };
        this.http.updateUser(updatedUser)
            .then(data => {
                this.common.setPassword(updatedUser.password);
                location.reload();
            });
    }

    checkPassword(): boolean {
        if (this.userData.oldPassword === '' && this.userData.newPassword === '' && this.userData.repNewPassword === '') {
            return true;
        } else if (this.userData.oldPassword !== '' && this.userData.newPassword !== '' && this.userData.repNewPassword !== '') {
            if (this.userData.oldPassword === this.common.getPassword()) {
                if (this.userData.oldPassword !== this.userData.newPassword) {
                    if (this.userData.newPassword === this.userData.repNewPassword) {
                        return true;
                    } else {
                        this.passwordAlerts.push('passwordnomatch');
                    }
                } else {
                    this.passwordAlerts.push('passwordsbedifferent');
                }
            } else {
                this.passwordAlerts.push('oldpasswordnomatch');
            }
        } else {
            this.passwordAlerts.push('passwordfieldsrequired');
        }
        return false;
    }
}
