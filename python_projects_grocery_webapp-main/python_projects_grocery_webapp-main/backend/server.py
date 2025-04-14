from flask import Flask, request, jsonify
from sql_connection import get_sql_connection
import products_dao
import orders_dao
import uom_dao
import json

app = Flask(__name__)
connection = get_sql_connection()

@app.route('/getUOM', methods=['GET'])
def get_uom():
    response = uom_dao.get_uoms(connection)
    return jsonify(response), 200

@app.route('/getProducts', methods=['GET'])
def get_products():
    response = products_dao.get_all_products(connection)
    return jsonify(response), 200

@app.route('/insertProduct', methods=['POST'])
def insert_product():
    request_payload = json.loads(request.form['data'])
    product_id = products_dao.insert_new_product(connection, request_payload)
    return jsonify({'product_id': product_id}), 201

@app.route('/getAllOrders', methods=['GET'])
def get_all_orders():
    response = orders_dao.get_all_orders(connection)
    return jsonify(response), 200

@app.route('/insertOrder', methods=['POST'])
def insert_order():
    request_payload = json.loads(request.form['data'])
    order_id = orders_dao.insert_order(connection, request_payload)
    return jsonify({'order_id': order_id}), 201

@app.route('/deleteProduct', methods=['POST'])
def delete_product():
    product_id = request.form['product_id']
    deleted_id = products_dao.delete_product(connection, product_id)
    return jsonify({'product_id': deleted_id}), 200

if __name__ == "__main__":
    print("Starting Python Flask Server for Grocery Store Management System...")
    app.run(port=5000, debug=True)