USE `ry-vue`;

-- 1. 用户表
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
                       username VARCHAR(50) UNIQUE NOT NULL COMMENT '用户名',
                       email VARCHAR(100) UNIQUE NOT NULL COMMENT '邮箱',
                       password_hash VARCHAR(255) NOT NULL COMMENT '密码哈希',
                       display_name VARCHAR(100) COMMENT '显示名称',
                       avatar_url VARCHAR(255) COMMENT '头像URL',
                       bio TEXT COMMENT '个人简介',
                       role ENUM('ADMIN', 'MODERATOR', 'USER', 'GUEST') DEFAULT 'USER' COMMENT '角色',
                       reputation INT DEFAULT 0 COMMENT '声望值',
                       status ENUM('ACTIVE', 'INACTIVE', 'SUSPENDED', 'BANNED') DEFAULT 'ACTIVE' COMMENT '状态',
                       last_login_at TIMESTAMP NULL COMMENT '最后登录时间',
                       email_verified BOOLEAN DEFAULT FALSE COMMENT '邮箱是否验证',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                       INDEX idx_username (username),
                       INDEX idx_email (email),
                       INDEX idx_status (status),
                       INDEX idx_role (role)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 2. 反馈分类表
CREATE TABLE categories (
                            id INT AUTO_INCREMENT PRIMARY KEY COMMENT '分类ID',
                            name VARCHAR(50) NOT NULL COMMENT '分类名称',
                            slug VARCHAR(50) UNIQUE NOT NULL COMMENT '分类URL标识',
                            description TEXT COMMENT '分类描述',
                            parent_id INT NULL COMMENT '父分类ID',
                            sort_order INT DEFAULT 0 COMMENT '排序顺序',
                            is_active BOOLEAN DEFAULT TRUE COMMENT '是否激活',
                            icon VARCHAR(100) COMMENT '分类图标',
                            color VARCHAR(20) COMMENT '分类颜色',
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            FOREIGN KEY (parent_id) REFERENCES categories(id) ON DELETE SET NULL,
                            INDEX idx_slug (slug),
                            INDEX idx_parent (parent_id),
                            INDEX idx_active (is_active)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='反馈分类表';

-- 3. 反馈意见表（核心表）
CREATE TABLE feedbacks (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '反馈ID',
                           user_id BIGINT NOT NULL COMMENT '提交用户ID',
                           category_id INT NOT NULL COMMENT '分类ID',
                           title VARCHAR(200) NOT NULL COMMENT '反馈标题',
                           slug VARCHAR(255) UNIQUE NOT NULL COMMENT '反馈URL标识',
                           content TEXT NOT NULL COMMENT '反馈内容',
                           summary VARCHAR(500) COMMENT '内容摘要',
                           status ENUM('PENDING', 'UNDER_REVIEW', 'PLANNED', 'IN_PROGRESS', 'COMPLETED', 'REJECTED', 'DUPLICATE') DEFAULT 'PENDING' COMMENT '状态',
                           priority ENUM('LOW', 'MEDIUM', 'HIGH', 'CRITICAL') DEFAULT 'MEDIUM' COMMENT '优先级',
                           visibility ENUM('PUBLIC', 'PRIVATE', 'INTERNAL') DEFAULT 'PUBLIC' COMMENT '可见性',
                           vote_count INT DEFAULT 0 COMMENT '投票总数',
                           comment_count INT DEFAULT 0 COMMENT '评论总数',
                           view_count INT DEFAULT 0 COMMENT '查看次数',
                           featured BOOLEAN DEFAULT FALSE COMMENT '是否精选',
                           anonymous BOOLEAN DEFAULT FALSE COMMENT '是否匿名',
                           allow_comments BOOLEAN DEFAULT TRUE COMMENT '是否允许评论',
                           tags JSON COMMENT '标签列表',
                           attachments_count INT DEFAULT 0 COMMENT '附件数量',
                           estimated_effort INT COMMENT '预计工作量（小时）',
                           estimated_completion_date DATE COMMENT '预计完成日期',
                           actual_completion_date DATE COMMENT '实际完成日期',
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                           closed_at TIMESTAMP NULL COMMENT '关闭时间',
                           closed_by BIGINT NULL COMMENT '关闭人ID',
                           FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                           FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE,
                           FOREIGN KEY (closed_by) REFERENCES users(id) ON DELETE SET NULL,
                           INDEX idx_user (user_id),
                           INDEX idx_category (category_id),
                           INDEX idx_status (status),
                           INDEX idx_priority (priority),
                           INDEX idx_created (created_at),
                           INDEX idx_votes (vote_count),
                           INDEX idx_featured (featured),
                           FULLTEXT idx_content (title, content, summary)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='反馈意见表';

-- 4. 投票表
CREATE TABLE votes (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '投票ID',
                       feedback_id BIGINT NOT NULL COMMENT '反馈ID',
                       user_id BIGINT NOT NULL COMMENT '用户ID',
                       vote_type ENUM('UPVOTE', 'DOWNVOTE') NOT NULL COMMENT '投票类型',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                       UNIQUE KEY uk_feedback_user (feedback_id, user_id), -- 每个用户对每个反馈只能投一次
                       FOREIGN KEY (feedback_id) REFERENCES feedbacks(id) ON DELETE CASCADE,
                       FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                       INDEX idx_feedback (feedback_id),
                       INDEX idx_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='投票表';

-- 5. 评论表
CREATE TABLE comments (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '评论ID',
                          feedback_id BIGINT NOT NULL COMMENT '反馈ID',
                          user_id BIGINT NOT NULL COMMENT '用户ID',
                          parent_id BIGINT NULL COMMENT '父评论ID',
                          content TEXT NOT NULL COMMENT '评论内容',
                          status ENUM('PUBLISHED', 'PENDING', 'DELETED', 'HIDDEN') DEFAULT 'PUBLISHED' COMMENT '状态',
                          is_author BOOLEAN DEFAULT FALSE COMMENT '是否为反馈作者',
                          is_official BOOLEAN DEFAULT FALSE COMMENT '是否为官方回复',
                          vote_count INT DEFAULT 0 COMMENT '赞同数',
                          report_count INT DEFAULT 0 COMMENT '被举报次数',
                          metadata JSON COMMENT '附加数据',
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          deleted_at TIMESTAMP NULL COMMENT '删除时间',
                          FOREIGN KEY (feedback_id) REFERENCES feedbacks(id) ON DELETE CASCADE,
                          FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                          FOREIGN KEY (parent_id) REFERENCES comments(id) ON DELETE CASCADE,
                          INDEX idx_feedback (feedback_id),
                          INDEX idx_user (user_id),
                          INDEX idx_parent (parent_id),
                          INDEX idx_status (status),
                          INDEX idx_created (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

-- 6. 附件表
CREATE TABLE attachments (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '附件ID',
                             feedback_id BIGINT NOT NULL COMMENT '反馈ID',
                             user_id BIGINT NOT NULL COMMENT '上传用户ID',
                             file_name VARCHAR(255) NOT NULL COMMENT '文件名',
                             file_path VARCHAR(500) NOT NULL COMMENT '文件路径',
                             file_size BIGINT NOT NULL COMMENT '文件大小（字节）',
                             file_type VARCHAR(100) COMMENT '文件类型',
                             mime_type VARCHAR(100) COMMENT 'MIME类型',
                             original_name VARCHAR(255) COMMENT '原始文件名',
                             thumbnail_path VARCHAR(500) COMMENT '缩略图路径',
                             is_image BOOLEAN DEFAULT FALSE COMMENT '是否为图片',
                             width INT COMMENT '图片宽度',
                             height INT COMMENT '图片高度',
                             upload_ip VARCHAR(45) COMMENT '上传IP',
                             download_count INT DEFAULT 0 COMMENT '下载次数',
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
                             FOREIGN KEY (feedback_id) REFERENCES feedbacks(id) ON DELETE CASCADE,
                             FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                             INDEX idx_feedback (feedback_id),
                             INDEX idx_user (user_id),
                             INDEX idx_file_type (file_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='附件表';

-- 7. 标签表
CREATE TABLE tags (
                      id INT AUTO_INCREMENT PRIMARY KEY COMMENT '标签ID',
                      name VARCHAR(50) UNIQUE NOT NULL COMMENT '标签名称',
                      slug VARCHAR(50) UNIQUE NOT NULL COMMENT '标签URL标识',
                      description VARCHAR(200) COMMENT '标签描述',
                      color VARCHAR(20) COMMENT '标签颜色',
                      usage_count INT DEFAULT 0 COMMENT '使用次数',
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                      INDEX idx_slug (slug),
                      INDEX idx_usage (usage_count)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签表';

-- 8. 反馈-标签关联表
CREATE TABLE feedback_tags (
                               feedback_id BIGINT NOT NULL COMMENT '反馈ID',
                               tag_id INT NOT NULL COMMENT '标签ID',
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               PRIMARY KEY (feedback_id, tag_id),
                               FOREIGN KEY (feedback_id) REFERENCES feedbacks(id) ON DELETE CASCADE,
                               FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE,
                               INDEX idx_tag (tag_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='反馈标签关联表';

-- 9. 关注表
CREATE TABLE subscriptions (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '关注ID',
                               user_id BIGINT NOT NULL COMMENT '用户ID',
                               feedback_id BIGINT NULL COMMENT '关注的反馈ID',
                               category_id INT NULL COMMENT '关注的分类ID',
                               subscription_type ENUM('FEEDBACK', 'CATEGORY', 'USER') NOT NULL COMMENT '关注类型',
                               notify_email BOOLEAN DEFAULT TRUE COMMENT '是否邮件通知',
                               notify_push BOOLEAN DEFAULT TRUE COMMENT '是否推送通知',
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '关注时间',
                               UNIQUE KEY uk_user_subscription (user_id, feedback_id, category_id, subscription_type),
                               FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                               FOREIGN KEY (feedback_id) REFERENCES feedbacks(id) ON DELETE CASCADE,
                               FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE,
                               INDEX idx_user (user_id),
                               INDEX idx_feedback (feedback_id),
                               INDEX idx_category (category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='关注订阅表';

-- 10. 状态变更历史表
CREATE TABLE status_history (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '历史ID',
                                feedback_id BIGINT NOT NULL COMMENT '反馈ID',
                                old_status VARCHAR(50) COMMENT '旧状态',
                                new_status VARCHAR(50) COMMENT '新状态',
                                changed_by BIGINT NOT NULL COMMENT '变更人ID',
                                reason TEXT COMMENT '变更原因',
                                comment TEXT COMMENT '附加说明',
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '变更时间',
                                FOREIGN KEY (feedback_id) REFERENCES feedbacks(id) ON DELETE CASCADE,
                                FOREIGN KEY (changed_by) REFERENCES users(id) ON DELETE CASCADE,
                                INDEX idx_feedback (feedback_id),
                                INDEX idx_changed_by (changed_by),
                                INDEX idx_created (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='状态变更历史表';

-- 11. 举报表
CREATE TABLE reports (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '举报ID',
                         reporter_id BIGINT NOT NULL COMMENT '举报人ID',
                         target_type ENUM('FEEDBACK', 'COMMENT', 'USER') NOT NULL COMMENT '举报目标类型',
                         target_id BIGINT NOT NULL COMMENT '目标ID',
                         reason ENUM('SPAM', 'INAPPROPRIATE', 'ABUSIVE', 'ILLEGAL', 'OTHER') NOT NULL COMMENT '举报原因',
                         description TEXT COMMENT '详细描述',
                         status ENUM('PENDING', 'UNDER_REVIEW', 'RESOLVED', 'DISMISSED') DEFAULT 'PENDING' COMMENT '处理状态',
                         resolved_by BIGINT NULL COMMENT '处理人ID',
                         resolution TEXT COMMENT '处理结果',
                         resolved_at TIMESTAMP NULL COMMENT '处理时间',
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '举报时间',
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                         FOREIGN KEY (reporter_id) REFERENCES users(id) ON DELETE CASCADE,
                         FOREIGN KEY (resolved_by) REFERENCES users(id) ON DELETE SET NULL,
                         INDEX idx_reporter (reporter_id),
                         INDEX idx_target (target_type, target_id),
                         INDEX idx_status (status),
                         INDEX idx_created (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='举报表';

-- 12. 通知表
CREATE TABLE notifications (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '通知ID',
                               user_id BIGINT NOT NULL COMMENT '接收用户ID',
                               type ENUM('NEW_FEEDBACK', 'NEW_COMMENT', 'STATUS_CHANGE', 'NEW_REPLY', 'MENTION', 'SYSTEM') NOT NULL COMMENT '通知类型',
                               title VARCHAR(200) NOT NULL COMMENT '通知标题',
                               content TEXT NOT NULL COMMENT '通知内容',
                               reference_type VARCHAR(50) COMMENT '关联类型',
                               reference_id BIGINT COMMENT '关联ID',
                               is_read BOOLEAN DEFAULT FALSE COMMENT '是否已读',
                               is_email_sent BOOLEAN DEFAULT FALSE COMMENT '邮件是否已发送',
                               metadata JSON COMMENT '附加数据',
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               read_at TIMESTAMP NULL COMMENT '阅读时间',
                               FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                               INDEX idx_user (user_id),
                               INDEX idx_type (type),
                               INDEX idx_read (is_read),
                               INDEX idx_created (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知表';

-- 13. 活动日志表
CREATE TABLE activity_logs (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '日志ID',
                               user_id BIGINT NULL COMMENT '用户ID（可为空，表示系统操作）',
                               action_type VARCHAR(100) NOT NULL COMMENT '操作类型',
                               target_type VARCHAR(50) COMMENT '目标类型',
                               target_id BIGINT COMMENT '目标ID',
                               description TEXT COMMENT '操作描述',
                               ip_address VARCHAR(45) COMMENT 'IP地址',
                               user_agent TEXT COMMENT '用户代理',
                               metadata JSON COMMENT '附加数据',
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
                               INDEX idx_user (user_id),
                               INDEX idx_action (action_type),
                               INDEX idx_target (target_type, target_id),
                               INDEX idx_created (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动日志表';

-- 14. 系统配置表
CREATE TABLE system_settings (
                                 id INT AUTO_INCREMENT PRIMARY KEY COMMENT '配置ID',
                                 setting_key VARCHAR(100) UNIQUE NOT NULL COMMENT '配置键',
                                 setting_value TEXT COMMENT '配置值',
                                 setting_type ENUM('STRING', 'NUMBER', 'BOOLEAN', 'JSON', 'TEXT') DEFAULT 'STRING' COMMENT '值类型',
                                 category VARCHAR(50) COMMENT '配置分类',
                                 description VARCHAR(200) COMMENT '配置描述',
                                 is_public BOOLEAN DEFAULT FALSE COMMENT '是否公开',
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 INDEX idx_key (setting_key),
                                 INDEX idx_category (category)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统配置表';