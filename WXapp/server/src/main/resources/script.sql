-- 查询常用用户
-- 按照名称模糊查询用户
SELECT
  wx.nick_name,
  wx.avatar_url
FROM user u
  LEFT JOIN user_wx wx ON wx.user_id = u.id
WHERE u.name LIKE '%xxx%';

-- 判断微信用户是否存在
SELECT *
FROM htd_lunch.user_wx
WHERE nick_name = ''
      AND gender = ''
      AND language = ''
      AND city = '';

INSERT INTO htd_lunch.user (NAME) VALUES ('陶呵呵');
INSERT INTO htd_lunch.user_wx (
  nick_name, gender, language, city, province, country, user_id, avatar_url
) VALUES (
  '陶呵呵', '1', 'zh_CN', '', 'Firenze', 'Italy', '1',
  'https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEJrjrX8xptUibYibicn4dvplVIWBrRhTyDIIPTar7uHl86KMjX92HMIxIGZV92PCcTiaqy0gTP4jKxOWQ/132'
);

INSERT INTO htd_lunch.menu (title, create_time, creater_id) VALUES ('5月16日菜单', '2018-05-19 12:00:00', '');
