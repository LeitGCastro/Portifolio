import json
import os

file_path = 'lista_tarefas.json'
tasks = [
        # {
        # 'Cozinhar': {
        #     'description': 'Macarrão', 
        #     'requirements': 'Comprar o macarrão'
        #     },
        # }
    ]
remake_dict = {}

def update_json(tasks_list):
    with open(file_path, 'w', encoding='utf-8') as file:
        json.dump(
            tasks_list,
            file,
            ensure_ascii=False,
            indent=4
        )

def list_tasks():
    with open(file_path, 'r', encoding='utf-8') as file:
        tasks_list = json.load(file)
    return tasks_list

def unmake_tasks():
    tasks_list = list_tasks()
    # remove o ultimo item, joga na memória e chama a func que atualiza o json
    remake_dict = tasks_list.pop()
    update_json(tasks_list)
    pretty_print()

def remake_task(tasks, remake_dict):    
    tasks.append(remake_dict)
    update_json(tasks)
    pretty_print()

def add_task(tasks):
    task_title = user_input
    description = input('Descrição das tarefa: ')
    requirements = input('Dependencias da tarefa: ')
    
    tasks.append({task_title: {'description': description, 'requirements': requirements}},)
    update_json(tasks)
    pretty_print()

def pretty_print():
    print(json.dumps(list_tasks(), indent=4))
    

print('Bem vindo a lista de tarefas!\n')
update_json(tasks)

while True:
    print('Comandos: Listar, Desfazer, Refazer')
    user_input = input('Adicione uma tarefa a lista! ')

    # Elemina os if/else
    comandos = {
        #lambda serve para manter a função em espera (closure)
        'listar': lambda: pretty_print(),
        'desfazer': lambda: unmake_tasks(),
        'refazer': lambda: remake_task(tasks, remake_dict),
        'adicionar': lambda: add_task(tasks),
        'clear': lambda: os.system('clear')
    }

    # pega o retorno de comandos de acordo com o input do usuário 
    if comandos.get(user_input) is not None:         
        comando = comandos.get(user_input)
    else:
        comando = comandos['adicionar']

    comando()
    





