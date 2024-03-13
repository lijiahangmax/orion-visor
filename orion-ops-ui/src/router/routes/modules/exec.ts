import type { AppRouteRecordRaw } from '../types';
import { DEFAULT_LAYOUT } from '../base';

const EXEC: AppRouteRecordRaw = {
  name: 'execModule',
  path: '/exec-module',
  component: DEFAULT_LAYOUT,
  children: [
    {
      name: 'execTemplate',
      path: '/exec-template',
      component: () => import('@/views/exec/exec-template/index.vue'),
    },
    {
      name: 'execLog',
      path: '/exec-log',
      component: () => import('@/views/exec/exec-log/index.vue'),
    },
    {
      name: 'execHostLog',
      path: '/exec-host-log',
      component: () => import('@/views/exec/exec-host-log/index.vue'),
    },
  ],
};

export default EXEC;
