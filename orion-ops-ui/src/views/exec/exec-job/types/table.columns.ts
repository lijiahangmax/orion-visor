import type { TableColumnData } from '@arco-design/web-vue/es/table/interface';
import { dateFormat } from '@/utils';

const columns = [
  {
    title: 'id',
    dataIndex: 'id',
    slotName: 'id',
    width: 70,
    align: 'left',
    fixed: 'left',
  }, {
    title: '任务名称',
    dataIndex: 'name',
    slotName: 'name',
    align: 'left',
    width: 180,
    ellipsis: true,
  }, {
    title: 'cron',
    dataIndex: 'expression',
    slotName: 'expression',
    align: 'left',
    width: 168,
    ellipsis: true,
    tooltip: true,
  }, {
    title: '执行命令',
    dataIndex: 'command',
    slotName: 'command',
    align: 'left',
    ellipsis: true,
    tooltip: true,
  }, {
    title: '任务状态',
    dataIndex: 'status',
    slotName: 'status',
    align: 'center',
    width: 112,
  }, {
    title: '最近任务',
    dataIndex: 'recentLog',
    slotName: 'recentLog',
    align: 'left',
    width: 184,
  }, {
    title: '修改时间',
    dataIndex: 'updateTime',
    slotName: 'updateTime',
    align: 'center',
    width: 180,
    render: ({ record }) => {
      return dateFormat(new Date(record.updateTime));
    },
  }, {
    title: '操作',
    slotName: 'handle',
    width: 228,
    align: 'center',
    fixed: 'right',
  },
] as TableColumnData[];

export default columns;