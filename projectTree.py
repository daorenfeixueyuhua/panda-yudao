# 主要为了输出maven的项目结构
import pathlib

SRC_DIR = 'src'
# 排除的文件夹
EX_INCLUDE_DIR = ['nginx', 'node_modules', '${project.basedir}']


def print_dir(path, level):
    # 如果这层包含 src 就默认已经到项目了，就不在往下走
    for i in path.iterdir():
        if i.name == SRC_DIR:
            print_format_dir(path, level)
            return
    print_format_dir(path, level)
    for i in path.iterdir():
        if i.is_dir() and i.name.startswith(".") is False and (i.name in EX_INCLUDE_DIR) is False:
            print_dir(i, level + 1)


def print_format_dir(path, level):
    print('|' + (' ' * 4 + '|') * level + '-' + path.name)


if __name__ == '__main__':
    rootPath = pathlib.Path.cwd()
    print_dir(rootPath, 0)
