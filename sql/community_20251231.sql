-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('系统配置', '1', '1', 'Opinion_configuration', 'system/Opinion_configuration/index', 1, 0, 'C', '0', '0', 'system:Opinion_configuration:list', '#', 'admin', sysdate(), '', null, '系统配置菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('系统配置查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:Opinion_configuration:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('系统配置新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:Opinion_configuration:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('系统配置修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:Opinion_configuration:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('系统配置删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:Opinion_configuration:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('系统配置导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:Opinion_configuration:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('反馈分类', '1', '1', 'categories', 'system/categories/index', 1, 0, 'C', '0', '0', 'system:categories:list', '#', 'admin', sysdate(), '', null, '反馈分类菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('反馈分类查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:categories:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('反馈分类新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:categories:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('反馈分类修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:categories:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('反馈分类删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:categories:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('反馈分类导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:categories:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('标签', '1', '1', 'tags', 'system/tags/index', 1, 0, 'C', '0', '0', 'system:tags:list', '#', 'admin', sysdate(), '', null, '标签菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('标签查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:tags:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('标签新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:tags:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('标签修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:tags:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('标签删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:tags:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('标签导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:tags:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户', '1', '1', 'users', 'system/users/index', 1, 0, 'C', '0', '0', 'system:users:list', '#', 'admin', sysdate(), '', null, '用户菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:users:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:users:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:users:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:users:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:users:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('投票', '1', '1', 'votes', 'system/votes/index', 1, 0, 'C', '0', '0', 'system:votes:list', '#', 'admin', sysdate(), '', null, '投票菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('投票查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:votes:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('投票新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:votes:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('投票修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:votes:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('投票删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:votes:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('投票导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:votes:export',       '#', 'admin', sysdate(), '', null, '');


-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('举报', '1', '1', 'reports', 'system/reports/index', 1, 0, 'C', '0', '0', 'system:reports:list', '#', 'admin', sysdate(), '', null, '举报菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('举报查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:reports:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('举报新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:reports:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('举报修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:reports:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('举报删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:reports:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('举报导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:reports:export',       '#', 'admin', sysdate(), '', null, '');


-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('状态变更历史', '1', '1', 'history', 'system/history/index', 1, 0, 'C', '0', '0', 'system:history:list', '#', 'admin', sysdate(), '', null, '状态变更历史菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('状态变更历史查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:history:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('状态变更历史新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:history:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('状态变更历史修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:history:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('状态变更历史删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:history:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('状态变更历史导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:history:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('关注订阅', '1', '1', 'subscriptions', 'system/subscriptions/index', 1, 0, 'C', '0', '0', 'system:subscriptions:list', '#', 'admin', sysdate(), '', null, '关注订阅菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('关注订阅查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:subscriptions:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('关注订阅新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:subscriptions:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('关注订阅修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:subscriptions:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('关注订阅删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:subscriptions:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('关注订阅导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:subscriptions:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动日志', '1', '1', 'logs', 'system/logs/index', 1, 0, 'C', '0', '0', 'system:logs:list', '#', 'admin', sysdate(), '', null, '活动日志菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动日志查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:logs:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动日志新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:logs:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动日志修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:logs:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动日志删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:logs:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('活动日志导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:logs:export',       '#', 'admin', sysdate(), '', null, '');


-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('附件', '1', '1', 'attachments', 'system/attachments/index', 1, 0, 'C', '0', '0', 'system:attachments:list', '#', 'admin', sysdate(), '', null, '附件菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('附件查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:attachments:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('附件新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:attachments:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('附件修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:attachments:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('附件删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:attachments:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('附件导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:attachments:export',       '#', 'admin', sysdate(), '', null, '');


-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('评论', '1', '1', 'comments', 'system/comments/index', 1, 0, 'C', '0', '0', 'system:comments:list', '#', 'admin', sysdate(), '', null, '评论菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('评论查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:comments:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('评论新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:comments:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('评论修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:comments:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('评论删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:comments:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('评论导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:comments:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('反馈标签关联', '1', '1', 'tags', 'system/tags/index', 1, 0, 'C', '0', '0', 'system:tags:list', '#', 'admin', sysdate(), '', null, '反馈标签关联菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('反馈标签关联查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:tags:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('反馈标签关联新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:tags:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('反馈标签关联修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:tags:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('反馈标签关联删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:tags:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('反馈标签关联导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:tags:export',       '#', 'admin', sysdate(), '', null, '');


-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('反馈意见', '1', '1', 'feedbacks', 'system/feedbacks/index', 1, 0, 'C', '0', '0', 'system:feedbacks:list', '#', 'admin', sysdate(), '', null, '反馈意见菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('反馈意见查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:feedbacks:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('反馈意见新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:feedbacks:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('反馈意见修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:feedbacks:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('反馈意见删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:feedbacks:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('反馈意见导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:feedbacks:export',       '#', 'admin', sysdate(), '', null, '');


-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('通知', '1', '1', 'notifications', 'system/notifications/index', 1, 0, 'C', '0', '0', 'system:notifications:list', '#', 'admin', sysdate(), '', null, '通知菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('通知查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:notifications:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('通知新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:notifications:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('通知修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:notifications:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('通知删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:notifications:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('通知导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:notifications:export',       '#', 'admin', sysdate(), '', null, '');


