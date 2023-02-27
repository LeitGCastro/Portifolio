print('This Software calculates your BMI (Body mass index) based on your height and weight. \nPlease enter with the following informations.')

# Verify if the user input is a float number
def isFloat(case):
    try:
        float(case)
        return True
    except:
        return False

def calculate_bmi(weight, height):
    return (weight)/(height**2)

def verify_adult_bmi(bmi):
    if bmi<18.5:
        categorie='Underweight'
    elif bmi>=18.5 and bmi<=24.9:
        categorie='Appropriate weight'
    elif bmi>=25 and bmi<=29.9:
        categorie='Overweight'
    elif bmi>=30:
        categorie='Obesity'

    return categorie

def verify_teens_bmi(age, gender, bmi):

    # Women younger than 20
    if age<20 and gender=='Mrs':
        if age>=10 and age<13:
            if bmi<15: categorie="Underweight"
            elif bmi>=15 and bmi<20 : categorie='Appropriate weight'
            elif bmi>=20: categorie='Overweight'
        if age>=13 and age<16:
            if bmi<16.40: categorie="Underweight"
            elif bmi>=16.4 and bmi<23 : categorie='Appropriate weight'
            elif bmi>=23: categorie='Overweight'
        if age>=16:
            if bmi<17: categorie="Underweight"
            elif bmi>=17 and bmi<24.5 : categorie='Appropriate weight'
            elif bmi>=24.5: categorie='Overweight'
        else:
            print('Sorry but your age is out of range')
    # Men younger than 20
    elif age<20 and gender=='Mr':
        if age>=10 and age<13:
            if bmi<15.20: categorie="Underweight"
            elif bmi>=15.20 and bmi<19.50 : categorie='Appropriate weight'
            elif bmi>=19.50: categorie='Overweight'
        if age>=13 and age<16:
            if bmi<16.6: categorie="Underweight"
            elif bmi>=16.6 and bmi<20.5 : categorie='Appropriate weight'
            elif bmi>=20.5: categorie='Overweight'
        if age>=16:
            if bmi<18: categorie="Underweight"
            elif bmi>=18 and bmi<23 : categorie='Appropriate weight'
            elif bmi>=23: categorie='Overweight'
        else:
            print('Sorry but your age is out of range')

    return categorie

userData={}
# Name varification
while True:
    allowedChars = set('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ')
    case=input(' - Full Name: ') 
    verifyCase = set(case)
    if verifyCase.issubset(allowedChars):
        userData['name'] =  case
        break
    else:
        print('Please enter with a string!')
# Gender verification
while True:
    case=input(' - Gender: ').lower()
    if case=='male' or case=='female':
        if(case=='male'): 
            case='Mr'
        else:
            case='Mrs'
        userData['gender'] =  case
        break
    else:
        print('Please enter with male or female!')
# Age verification
while True:
    case=input(' - Age: ')
    if case.isdigit() and int(case)<120:
        userData['age'] = int(case)
        break
    else:
        print('Please enter with a valible age!')
# Weight verification
while True:
    case=input(' - Weight (kg): ')
    case=case.replace(',','.')
    if isFloat(case):
        if len(case)>4:
            print("Your weight input is too large! It must have less than 3 numbers.")
        else:
            userData['weight'] = float(case)
            break
    else:
        print('Please enter with a valible weight!')
# Height verification
while True:
    case=input(' - Height (m): ')
    case=case.replace(',','.')
    if isFloat(case):
        if '.' in case:    
            if len(case)<=4:
                userData['height'] = float(case)
                break
            else:
                print("Your height input is too large! It must have less than 3 numbers.")
        else:
            if len(case)<=3:
                userData['height'] = float(case)/100
                break
            else:
                print("Your height input is too large! It must have less than 3 numbers.")
    else:
        print('Please enter with a valible height!')

bmi = calculate_bmi(userData['weight'], userData['height'])

if userData['age']>=20:
    categorie = verify_adult_bmi(bmi)
else:
    categorie = verify_teens_bmi(userData['age'], userData['gender'], bmi)

print(f'{userData["gender"]} {userData["name"].capitalize()}, your BMI is {bmi:.2f}, you are categorized as {categorie}!')
