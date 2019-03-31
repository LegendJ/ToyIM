FROM mysql:5.7

#将所需文件放到容器中
ADD ./mysql /etc/mysql

RUN chmod a+x /etc/mysql/setup.sh
EXPOSE 3306
ENV MYSQL_ALLOW_EMPTY_PASSWORD=yes
#设置容器启动时执行的命令
CMD ["sh", "/etc/mysql/setup.sh"]