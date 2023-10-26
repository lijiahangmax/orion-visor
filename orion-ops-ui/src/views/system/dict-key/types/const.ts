import { ValueTypeEnum } from './enum.types';

/**
 * 快捷定义字段
 */
export const definedExtraKeys = [{
  name: 'status',
  type: ValueTypeEnum.STRING.value
}, {
  name: 'type',
  type: ValueTypeEnum.STRING.value
}, {
  name: 'color',
  type: ValueTypeEnum.COLOR.value
}];

/**
 * 内置字段
 */
export const innerKeys = ['name', 'value', 'label'];

/**
 * 额外参数类型
 */
export interface ExtraParamType {
  name?: string;
  type?: string;
}