import { Status } from './Status';
import * as moment from 'moment';
import { User } from './User';
import { Vehicle } from './Vehicle';

export class Invoice {
    id: number;
    date: Date;
    cartrackerId: number;
    totalSum: number;
    status: Status;

    user: User;

    constructor(_id: number, _date: Date, _cartrackerId: number, _totalSum: number, _status: Status, _user: User) {
        this.id = _id;
        this.date = _date;
        this.cartrackerId = _cartrackerId;
        this.totalSum = _totalSum;
        this.status = _status;
        this.user = _user;
    }

    dateToString(): string {
        return moment(this.date).format('YYYY-MM');
    }

    totalSumToString(): string {
        let temp = '€' + this.totalSum;
        if (temp.indexOf('.') < 0) {
            temp = temp + ',-';
        } else {
            temp = temp.replace('.', ',');
            if (temp.substr(temp.indexOf(',') + 1).length < 2) {
                temp = temp + '0';
            }
        }
        return temp;
    }
}
