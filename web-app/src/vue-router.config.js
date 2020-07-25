/* jshint esversion: 6 */
import Login from './components/login.vue';
import Welcome from './components/welcome.vue';
import Main from './components/main.vue';
import Success from './components/success.vue';
import Failure from './components/failure.vue';

import Address from './components/main/address.vue';
import Meeting from './components/main/meeting.vue';
import Message from './components/main/message.vue';
import Notice from './components/main/notice.vue';
import Schedule from './components/main/schedule.vue';
import Worklog from './components/main/worklog.vue';

import AddAddress from './components/addition/address.vue';
import AddMeeting from './components/addition/meeting.vue';
import AddMessage from './components/addition/message.vue';
import AddNotice from './components/addition/notice.vue';
import AddSchedule from './components/addition/schedule.vue';
import AddWorklog from './components/addition/worklog.vue';

import ModModress from './components/modification/address.vue';
import ModMeeting from './components/modification/meeting.vue';
import ModNotice from './components/modification/notice.vue';
import ModSchedule from './components/modification/schedule.vue';
import ModWorklog from './components/modification/worklog.vue';

const Routers = [{
  path: '/login',
  component: Login
}, {
  path: '/main',
  component: Main,
  children: [
    {
      path: '/main',
      component: Welcome
    }, {
      path: '/main/success',
      component: Success
    }, {
      path: '/main/failure',
      component: Failure
    }, {
      path: '/main/address',
      component: Address
    }, {
      path: '/main/meeting',
      component: Meeting
    }, {
      path: '/main/message',
      component: Message
    }, {
      path: '/main/notice',
      component: Notice
    }, {
      path: '/main/schedule',
      component: Schedule
    }, {
      path: '/main/worklog',
      component: Worklog
    }, {
      path: '/main/add-address',
      component: AddAddress
    }, {
      path: '/main/add-meeting',
      component: AddMeeting
    }, {
      path: '/main/add-message',
      component: AddMessage
    }, {
      path: '/main/add-notice',
      component: AddNotice
    }, {
      path: '/main/add-schedule',
      component: AddSchedule
    }, {
      path: '/main/add-worklog',
      component: AddWorklog
    }, {
      path: '/main/mod-address',
      component: ModModress
    }, {
      path: '/main/mod-meeting',
      component: ModMeeting
    }, {
      path: '/main/mod-notice',
      component: ModNotice
    }, {
      path: '/main/mod-schedule',
      component: ModSchedule
    }, {
      path: '/main/mod-worklog',
      component: ModWorklog
    }]
}, {
  path: '/',
  component: Login
}, {
  path: '*',
  component: Login
}
];
const RouterConfig = {
  mode: 'history',
  routes: Routers
};
export default RouterConfig;