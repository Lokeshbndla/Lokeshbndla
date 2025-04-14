import mysql.connector

__cnx = None

def get_sql_connection():
    """
    Establishes and returns a MySQL database connection.
    """
    global __cnx
    if __cnx is None:
        print("Opening MySQL connection...")
        __cnx = mysql.connector.connect(
            user='root',
            password='9492055569',
            database='grocery_management'
        )
    return __cnx